package com.example.employeeservice.model;

import com.example.employeeservice.entity.EmployeeEntity;
import java.util.UUID;
import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
public record Employee(
    UUID id,
    String name,
    String surname,
    String position
) {

  public EmployeeEntity toEntity() {
    return EmployeeEntity.builder()
        .id(UUID.randomUUID())
        .name(name)
        .surname(surname)
        .position(position)
        .build();
  }
}
