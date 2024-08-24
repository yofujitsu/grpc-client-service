package com.yofujitsu.grpcclientservice.service.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import com.yofujitsu.grpcclientservice.entity.Data;
import com.yofujitsu.grpccommon.GRPCData;
import com.yofujitsu.grpccommon.ValueType;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import com.yofujitsu.grpccommon.DataServerGrpc;

import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GRPCDataServiceImpl implements GRPCDataService {

    @GrpcClient(value = "data-generator-blocking")
    private DataServerGrpc.DataServerBlockingStub blockingStub;

    @GrpcClient(value = "data-generator-async")
    private DataServerGrpc.DataServerStub asyncStub;

    /**
     * Sends a single data point to the gRPC server.
     *
     * @param data The data point to be sent.
     */
    @Override
    public void send(Data data) {
        // Create a GRPCData object from the data point.
        GRPCData request = GRPCData.newBuilder()
                .setHeroId(data.getHeroId()) // Set the hero ID.
                .setTimestamp(Timestamp.newBuilder()
                        .setSeconds(data.getTimestamp()
                                .toEpochSecond(ZoneOffset.UTC)) // Set the timestamp.
                        .build())
                .setValueType(ValueType.valueOf(data.getValueType().name())) // Set the value type.
                .build();

        // Send the data point to the gRPC server.
        blockingStub.addData(request);
    }

    /**
     * Sends a list of data points to the gRPC server asynchronously.
     *
     * @param data The list of data points to be sent.
     */
    @Override
    public void send(List<Data> data) {
        // Create a response observer for handling the response from the server.
        StreamObserver<Empty> responseObserver = new StreamObserver<Empty>() {
            @Override
            public void onNext(Empty value) {
                // This method is called for each received message from the server.
                // No action is taken in this implementation.
            }

            @Override
            public void onError(Throwable t) {
                // This method is called when an error occurs during the streaming.
                // No action is taken in this implementation.
            }

            @Override
            public void onCompleted() {
                // This method is called when the streaming is completed.
                // No action is taken in this implementation.
            }
        };

        // Create a request observer for sending the data points to the server.
        StreamObserver<GRPCData> requestObserver = asyncStub.addStreamOfData(responseObserver);

        // Iterate over the list of data points and send them to the server.
        for (Data d : data) {
            GRPCData request = GRPCData.newBuilder()
                    .setHeroId(d.getHeroId())
                    .setTimestamp(Timestamp.newBuilder()
                            .setSeconds(d.getTimestamp()
                                    .toEpochSecond(ZoneOffset.UTC))
                            .build())
                    .setValueType(ValueType.valueOf(d.getValueType().name()))
                    .build();
            requestObserver.onNext(request);
        }

        // Indicate the end of the stream.
        requestObserver.onCompleted();
    }

}
