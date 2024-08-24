package com.yofujitsu.grpcclientservice.mapper;

import com.yofujitsu.grpcclientservice.dto.DataDto;
import com.yofujitsu.grpcclientservice.entity.Data;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DataMapper extends Mappable<Data, DataDto> {
}
