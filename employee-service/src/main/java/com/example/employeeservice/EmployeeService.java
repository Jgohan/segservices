package com.example.employeeservice;

import com.example.employeeservice.model.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class EmployeeService {

    List<Employee> employees = new ArrayList<>();

    ResponseEntity<String> createEmployee(Employee employee) {
        if (employees.contains(employee)) {
            return new ResponseEntity<>(
                    "Employee " + employee + " already exists",
                    HttpStatus.CONFLICT
            );
        }
        employees.add(employee);
        return new ResponseEntity<>("Employee " + employee + " created", HttpStatus.OK);
    }

    ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    ResponseEntity<Employee> getEmployee(String name) {
        var result = employees.stream()
                              .filter(it -> it.getName().equals(name))
                              .findFirst()
                              .orElse(null);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    ResponseEntity<String> updateEmployee(Employee updatedEmployee) {
        var existedEmployee = findByName(updatedEmployee.getName());
        if (nonNull(existedEmployee)) {
            existedEmployee
                    .setName(updatedEmployee.getName())
                    .setSurname(updatedEmployee.getSurname())
                    .setPosition(updatedEmployee.getPosition());
            return new ResponseEntity<>(
                    "Employee " + updatedEmployee.getName() + " was updated",
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "Employee " + updatedEmployee.getName() + " wasn't found",
                    HttpStatus.CONFLICT
            );
        }
    }

    ResponseEntity<String> deleteEmployee(String name) {
        var existedEmployee = findByName(name);
        if (nonNull(existedEmployee)) {
            employees.remove(existedEmployee);
            return new ResponseEntity<>(
                    "Employee " + name + " was deleted",
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "Employee " + name + " wasn't found",
                    HttpStatus.CONFLICT
            );
        }
    }

    private Employee findByName(String name) {
        return employees.stream()
                        .filter(it -> it.getName().equals(name))
                        .findFirst()
                        .orElse(null);
    }

}
