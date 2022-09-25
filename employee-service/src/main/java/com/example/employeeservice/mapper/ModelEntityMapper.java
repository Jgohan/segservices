package com.example.employeeservice.mapper;

import com.example.employeeservice.entity.AbstractEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;

public interface ModelEntityMapper<M, E extends AbstractEntity> {

  M toModel(E entity);

  @BeanMapping(builder = @Builder(disableBuilder = true))
  E toEntity(M model);

}
