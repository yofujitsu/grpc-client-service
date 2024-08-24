package com.yofujitsu.grpcclientservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Data {
    private Long heroId;
    private LocalDateTime timestamp;
    private Double value;
    private ValueType valueType;
}
