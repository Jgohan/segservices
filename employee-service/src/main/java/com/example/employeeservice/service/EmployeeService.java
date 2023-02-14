package com.example.employeeservice.service;

import static java.util.stream.Collectors.toList;

import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;


  @Transactional
  public ResponseEntity<String> createEmployee(Employee employee) {
    var employeeToCreate = employeeMapper.toEntity(employee);
    employeeRepository.save(employeeToCreate);

    return new ResponseEntity<>("Employee created", HttpStatus.CREATED);
  }

  public ResponseEntity<List<Employee>> getEmployees() {
    var employees = employeeRepository.findAll().stream()
        .map(employeeMapper::toModel)
        .collect(toList());

    if (employees.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  public ResponseEntity<Employee> getEmployee(UUID id) {
    return employeeRepository.findById(id)
        .map(entity -> {
          var employee = employeeMapper.toModel(entity);
          return new ResponseEntity<>(employee, HttpStatus.OK);
        })
        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
  }

  @Transactional
  public ResponseEntity<String> updateEmployee(
      UUID id,
      Employee updatedEmployee
  ) {
    if (employeeRepository.notExistsById(id)) {
      return new ResponseEntity<>("Employee " + id + " wasn't found", HttpStatus.CONFLICT);
    }

    var updatedEntity = employeeMapper.toEntity(updatedEmployee);
    updatedEntity.setId(id);

    employeeRepository.save(updatedEntity);

    return new ResponseEntity<>("Employee " + id + " was updated", HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<String> deleteEmployee(UUID id) {
    if (employeeRepository.notExistsById(id)) {
      return new ResponseEntity<>("Employee " + id + " wasn't found", HttpStatus.CONFLICT);
    }

    employeeRepository.deleteById(id);
    return new ResponseEntity<>("Employee " + id + " was deleted", HttpStatus.OK);
  }

}
