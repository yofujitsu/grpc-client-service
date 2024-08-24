package com.yofujitsu.grpcclientservice.service.grpc;

import com.yofujitsu.grpcclientservice.entity.Data;

import java.util.List;

public interface GRPCDataService {

    void send(Data data);

    void send(List<Data> data);
}
