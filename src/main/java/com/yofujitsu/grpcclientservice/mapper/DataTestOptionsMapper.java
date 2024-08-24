package com.yofujitsu.grpcclientservice.mapper;

import com.yofujitsu.grpcclientservice.entity.test.DataTestOptions;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import com.yofujitsu.grpcclientservice.dto.DataTestOptionsDto;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
