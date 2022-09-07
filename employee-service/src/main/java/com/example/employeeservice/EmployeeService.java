package com.example.employeeservice;

import com.example.employeeservice.entity.EmployeeEntity;
import com.example.employeeservice.model.Employee;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;


  @Transactional
  ResponseEntity<String> createEmployee(Employee employee) {
    var createdEmployee = employeeRepository.save(employee.toEntity());
    return new ResponseEntity<>("Employee created", HttpStatus.CREATED);
  }

  ResponseEntity<List<Employee>> getEmployees() {
    var employees = StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
        .map(EmployeeEntity::toModel)
        .collect(Collectors.toList());

    if (employees.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(employees, HttpStatus.OK);
    }
  }

  ResponseEntity<Employee> getEmployee(UUID id) {
    return employeeRepository.findById(id)
        .map(entity -> new ResponseEntity<>(entity.toModel(), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
  }

  @Transactional
  ResponseEntity<String> updateEmployee(
      UUID id,
      Employee updatedEmployee
  ) {
    return employeeRepository.findById(id)
        .map(entity -> {
          employeeRepository.save(entity.updateFrom(updatedEmployee));
          return new ResponseEntity<>("Employee " + id + " was updated", HttpStatus.OK);
        }).orElseGet(
            () -> new ResponseEntity<>("Employee " + id + " wasn't found", HttpStatus.CONFLICT));
  }

  @Transactional
  ResponseEntity<String> deleteEmployee(UUID id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id);
      return new ResponseEntity<>("Employee " + id + " was deleted", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Employee " + id + " wasn't found", HttpStatus.CONFLICT);
    }
  }

}
