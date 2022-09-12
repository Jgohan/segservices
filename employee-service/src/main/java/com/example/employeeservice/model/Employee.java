package com.example.employeeservice.model;

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

}
