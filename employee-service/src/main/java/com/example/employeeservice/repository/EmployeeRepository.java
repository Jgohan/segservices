package com.example.employeeservice.repository;

import com.example.employeeservice.entity.EmployeeEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

  @Query(
      """
      SELECT count(*) = 0
      FROM employees AS employee
      WHERE employee.id = :employeeId
      """
  )
  boolean notExistsById(UUID employeeId);

}
