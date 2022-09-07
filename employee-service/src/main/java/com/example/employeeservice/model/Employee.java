package com.example.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class Employee {
    private String name;
    private String surname;
    private String position;
}
