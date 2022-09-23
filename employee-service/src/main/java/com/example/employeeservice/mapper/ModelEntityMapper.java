package com.example.employeeservice.mapper;

import com.example.employeeservice.entity.AbstractEntity;

public interface ModelEntityMapper<M, E extends AbstractEntity> {
  M toModel(E entity);
  E toEntity(M model);
}
