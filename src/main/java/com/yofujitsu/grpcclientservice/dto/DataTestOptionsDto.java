package com.yofujitsu.grpcclientservice.dto;

import com.yofujitsu.grpcclientservice.entity.ValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataTestOptionsDto {
    private int delayInSeconds;
    private ValueType[] valueTypes;
}