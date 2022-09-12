package com.example.employeeservice.mapper;

import com.example.employeeservice.entity.EmployeeEntity;
import com.example.employeeservice.model.Employee;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

  public Employee toModel(EmployeeEntity entity) {
    return Employee.builder()
        .id(entity.getId())
        .name(entity.getName())
        .surname(entity.getSurname())
        .position(entity.getPosition())
        .build();
  }

  public EmployeeEntity toEntity(Employee model) {
    return EmployeeEntity.builder()
        .id(UUID.randomUUID())
        .name(model.name())
        .surname(model.surname())
        .position(model.position())
        .build();
  }

}
