package com.example.employeeservice.mapper;

import com.example.employeeservice.entity.EmployeeEntity;
import com.example.employeeservice.model.Employee;
import java.util.UUID;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends ModelEntityMapper<Employee, EmployeeEntity> {

  @AfterMapping
  default void generateEntityId(@MappingTarget EmployeeEntity entity) {
    entity.setId(UUID.randomUUID());
  }

}
