package com.example.employeeservice;

import com.example.employeeservice.model.Employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    ResponseEntity<String> createEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createEmployee(newEmployee);
    }

    @GetMapping
    ResponseEntity<List<Employee>> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{name}")
    ResponseEntity<Employee> getEmployee(@PathVariable String name) {
        return employeeService.getEmployee(name);
    }

    @PutMapping("/{name}")
    ResponseEntity<String> updateEmployee(
        @PathVariable String name,
        @RequestBody Employee updatedEmployee
    ) {
        return employeeService.updateEmployee(updatedEmployee);
    }

    @DeleteMapping("/{name}")
    ResponseEntity<String> deleteEmployee(@PathVariable String name) {
        return employeeService.deleteEmployee(name);
    }

}
