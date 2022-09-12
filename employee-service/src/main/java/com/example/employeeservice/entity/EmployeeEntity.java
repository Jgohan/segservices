package com.example.employeeservice.entity;

import static java.util.Objects.nonNull;

import com.example.employeeservice.model.Employee;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "employees")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity extends AbstractEntity<UUID> {

  private String name;
  private String surname;
  private String position;


  public Employee toModel() {
    return Employee.builder()
        .id(id)
        .name(name)
        .surname(surname)
        .position(position)
        .build();
  }

  public EmployeeEntity updateFrom(Employee model) {
    var name = model.name();
    if (nonNull(name)) {
      setName(name);
    }

    var surname = model.surname();
    if (nonNull(surname)) {
      setSurname(surname);
    }

    var position = model.position();
    if (nonNull(surname)) {
      setPosition(position);
    }

    return this;
  }
}

