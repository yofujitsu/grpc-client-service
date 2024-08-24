package com.yofujitsu.grpcclientservice.entity.test;

import com.yofujitsu.grpcclientservice.entity.ValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataTestOptions {
    private int delayInSeconds;
    private ValueType[] valueTypes;
}
