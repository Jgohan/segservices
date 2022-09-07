package com.example.employeeservice;

import com.example.employeeservice.model.Employee;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

  private final EmployeeService employeeService;


  @PostMapping
  ResponseEntity<String> createEmployee(@RequestBody Employee newEmployee) {
    var response = employeeService.createEmployee(newEmployee);
    log.info("Create employee - " + response.getBody());
    return response;
  }

  @GetMapping
  ResponseEntity<List<Employee>> getEmployees() {
    log.info("Get all employees");
    return employeeService.getEmployees();
  }

  @GetMapping("/{id}")
  ResponseEntity<Employee> getEmployee(@PathVariable UUID id) {
    log.info("Get employee " + id);
    return employeeService.getEmployee(id);
  }

  @PutMapping("/{id}")
  ResponseEntity<String> updateEmployee(
      @PathVariable UUID id,
      @RequestBody Employee updatedEmployee
  ) {
    var response = employeeService.updateEmployee(id, updatedEmployee);
    log.info("Update employee - " + response.getBody());
    return response;
  }

  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteEmployee(@PathVariable UUID id) {
    var response = employeeService.deleteEmployee(id);
    log.info("Delete employee - " + response.getBody());
    return response;
  }

}
