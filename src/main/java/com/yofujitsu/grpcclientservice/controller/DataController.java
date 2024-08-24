package com.yofujitsu.grpcclientservice.controller;

import com.yofujitsu.grpcclientservice.dto.DataDto;
import com.yofujitsu.grpcclientservice.dto.DataTestOptionsDto;
import com.yofujitsu.grpcclientservice.entity.Data;
import com.yofujitsu.grpcclientservice.entity.test.DataTestOptions;
import com.yofujitsu.grpcclientservice.mapper.DataMapper;
import com.yofujitsu.grpcclientservice.mapper.DataTestOptionsMapper;
import com.yofujitsu.grpcclientservice.service.grpc.GRPCDataService;
import com.yofujitsu.grpcclientservice.service.test.TestDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {

    private final DataMapper dataMapper;
    private final GRPCDataService grpcDataService;
    private final DataTestOptionsMapper dataTestOptionsMapper;
    private final TestDataService testDataService;

    @PostMapping("/send")
    public void send(@RequestBody DataDto dataDto) {
        Data data = dataMapper.toEntity(dataDto);
        grpcDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto dataTestOptionsDto) {
        DataTestOptions dataTestOptions = dataTestOptionsMapper.toEntity(dataTestOptionsDto);
        testDataService.sendMessages(dataTestOptions);

    }
}
