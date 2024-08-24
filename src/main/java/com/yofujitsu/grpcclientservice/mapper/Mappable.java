package com.yofujitsu.grpcclientservice.mapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

public interface Mappable<E, D> {

    E toEntity(D dto);

    List<E> toEntityList(List<D> dtoList);

    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);
}
