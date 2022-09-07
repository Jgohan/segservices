package com.example.employeeservice.entity;

import static java.util.Objects.nonNull;

import com.example.employeeservice.model.Employee;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "employees")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Getter
@Setter
public class EmployeeEntity {

  @Id
  @EqualsAndHashCode.Include
  private UUID id;
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
    if (nonNull(name)) setName(name);

    var surname = model.surname();
    if (nonNull(surname)) setSurname(surname);

    var position = model.position();
    if (nonNull(surname)) setPosition(position);

    return this;
  }
}

