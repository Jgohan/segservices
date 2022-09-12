package com.example.departmentservice.controller

import com.example.departmentservice.service.DepartmentService
import com.example.departmentservice.model.Department
import com.example.departmentservice.util.lazyLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/departments")
class DepartmentController(
    @Autowired private val departmentService: DepartmentService
) {

    companion object {
        private val logger by lazyLogger<DepartmentController>()
    }

    @PostMapping
    fun createDepartment(
        @RequestBody newDepartment: Department
    ): ResponseEntity<String> =
        departmentService.createDepartment(newDepartment).also { response ->
            logger.info("Create department - ${response.body}")
        }

    @GetMapping
    fun getDepartments(): ResponseEntity<Iterable<Department>?> =
        departmentService.getDepartments().also { logger.info("Get all departments") }

    @GetMapping("/{id}")
    fun getDepartment(
        @PathVariable id: UUID
    ): ResponseEntity<Department?> =
        departmentService.getDepartment(id).also { logger.info("Get department $id") }

    @PutMapping("/{id}")
    fun updateDepartment(
        @PathVariable id: UUID,
        @RequestBody updatedDepartment: Department
    ): ResponseEntity<String> =
        departmentService.updateDepartment(id, updatedDepartment).also { response ->
            logger.info("Update department - ${response.body}")
        }

    @DeleteMapping("/{id}")
    fun deleteDepartment(
        @PathVariable id: UUID
    ): ResponseEntity<String> =
        departmentService.deleteDepartment(id).also { response ->
            logger.info("Delete department - ${response.body}")
        }
}