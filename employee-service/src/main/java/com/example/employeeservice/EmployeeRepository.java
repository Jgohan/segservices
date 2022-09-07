package com.example.employeeservice;

import com.example.employeeservice.entity.EmployeeEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, UUID> {

}
