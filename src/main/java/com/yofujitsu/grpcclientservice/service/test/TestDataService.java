package com.yofujitsu.grpcclientservice.service.test;

import com.yofujitsu.grpcclientservice.entity.test.DataTestOptions;

public interface TestDataService {

    void sendMessages(DataTestOptions dataTestOptions);
}
