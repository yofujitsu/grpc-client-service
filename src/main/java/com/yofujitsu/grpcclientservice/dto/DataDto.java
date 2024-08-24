package com.yofujitsu.grpcclientservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yofujitsu.grpcclientservice.entity.ValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class DataDto {
    private Long heroId;

    @JsonFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    private Double value;
    private ValueType valueType;
}
