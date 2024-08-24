package com.yofujitsu.grpcclientservice.service.test;

import com.yofujitsu.grpcclientservice.entity.Data;
import com.yofujitsu.grpcclientservice.entity.ValueType;
import com.yofujitsu.grpcclientservice.entity.test.DataTestOptions;
import com.yofujitsu.grpcclientservice.service.grpc.GRPCDataService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestDataServiceImpl implements TestDataService {

    private final ScheduledExecutorService executorService
            = Executors.newSingleThreadScheduledExecutor();
    private final GRPCDataService grpcDataService;

    @Value("${push.batch-size}")
    private int batchSize;


    /**
     * Sends messages to the gRPC server based on the provided {@link DataTestOptions}.
     *
     * @param dataTestOptions The options for sending messages.
     */
    @Override
    public void sendMessages(DataTestOptions dataTestOptions) {
        List<Data> dataBatch = new ArrayList<>();  // The batch of data messages to be sent.

        // If there are value types specified in the options, schedule a task to send messages periodically.
        if(dataTestOptions.getValueTypes().length > 0) {
            executorService.scheduleAtFixedRate(() -> {
                // Create a new data message with a random hero ID, random value type, and current timestamp.
                Data data = new Data();
                data.setHeroId(
                        (long) getRandomNumber(1, 125)
                );
                data.setValueType(
                        getRandomValueType(dataTestOptions.getValueTypes())
                );
                data.setTimestamp(
                        java.time.LocalDateTime.now()
                );

                // Add the data message to the batch.
                dataBatch.add(data);

                // If the batch is full, send it to the gRPC server and clear the batch.
                if(dataBatch.size() == batchSize) {
                    grpcDataService.send(dataBatch);
                    dataBatch.clear();
                }
            },
            0,  // The initial delay before sending the first message.
            dataTestOptions.getDelayInSeconds(),  // The delay between sending messages.
            TimeUnit.SECONDS  // The time unit for the delay.
            );
        }
    }

    private double getRandomNumber(double min, double max) {
        return ((Math.random() * (max - min)) + min);
    }

    private ValueType getRandomValueType(ValueType[] valueTypes) {
        int index = (int) (Math.random() * valueTypes.length);
        return valueTypes[index];
    }
}
