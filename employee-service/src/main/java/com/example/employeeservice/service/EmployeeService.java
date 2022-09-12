package com.example.employeeservice.service;

import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.mapper.EmployeeMapper;
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
  private final EmployeeMapper employeeMapper;


  @Transactional
  public ResponseEntity<String> createEmployee(Employee employee) {
    employeeRepository.save(employeeMapper.toEntity(employee));
    return new ResponseEntity<>("Employee created", HttpStatus.CREATED);
  }

  public ResponseEntity<List<Employee>> getEmployees() {
    var employees = StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
        .map(employeeMapper::toModel).collect(Collectors.toList());

    if (employees.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  public ResponseEntity<Employee> getEmployee(UUID id) {
    return employeeRepository.findById(id)
        .map(entity -> new ResponseEntity<>(employeeMapper.toModel(entity), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
  }

  @Transactional
  public ResponseEntity<String> updateEmployee(
      UUID id,
      Employee updatedEmployee
  ) {
    return employeeRepository.findById(id)
        .map(entity -> {
          employeeRepository.save(entity.updateFrom(updatedEmployee));
          return new ResponseEntity<>("Employee " + id + " was updated", HttpStatus.OK);
        })
        .orElseGet(() ->
            new ResponseEntity<>("Employee " + id + " wasn't found", HttpStatus.CONFLICT)
        );
  }

  @Transactional
  public ResponseEntity<String> deleteEmployee(UUID id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id);
      return new ResponseEntity<>("Employee " + id + " was deleted", HttpStatus.OK);
    }

    return new ResponseEntity<>("Employee " + id + " wasn't found", HttpStatus.CONFLICT);
  }

}
