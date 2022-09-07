package com.example.departmentservice

import com.example.departmentservice.model.Department
import com.example.departmentservice.util.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DepartmentService(
    @Autowired private val departmentRepository: DepartmentRepository
) {

    @Transactional
    fun createDepartment(newDepartment: Department): ResponseEntity<String> {
        return newDepartment.run {
            if (departmentRepository.existsByName(name)) {
                Response.conflict("Department $name already exists")
            } else {
                departmentRepository.save(newDepartment.toEntity())
                Response.created("Department $name was created")
            }
        }
    }

    fun getDepartments(): ResponseEntity<Iterable<Department>?> {
        val departments = departmentRepository.findAll().map { it.toModel() }

        return if (departments.isNotEmpty()) {
            Response.ok(departments)
        } else {
            Response.noContent(null)
        }
    }

    fun getDepartment(id: UUID): ResponseEntity<Department?> {
        val result = departmentRepository.findById(id)

        return if (result.isPresent) {
            Response.ok(result.get().toModel())
        } else {
            Response.noContent(null)
        }
    }

    @Transactional
    fun updateDepartment(
        id: UUID,
        updatedDepartment: Department
    ): ResponseEntity<String> {
        return updatedDepartment.run {
            when {
                departmentRepository.notExistsById(id) -> {
                    Response.conflict("Department $id wasn't found")
                }
                departmentRepository.existsByName(name) -> {
                    Response.conflict("Department $name already exists")
                }
                else -> {
                    departmentRepository.save(updatedDepartment.toEntity(id))
                    Response.ok("Department $id was edited")
                }
            }
        }
    }

    @Transactional
    fun deleteDepartment(id: UUID): ResponseEntity<String> {
        return if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id)
            Response.ok("Department $id was deleted")
        } else {
            Response.conflict("Department $id wasn't found")
        }
    }
}