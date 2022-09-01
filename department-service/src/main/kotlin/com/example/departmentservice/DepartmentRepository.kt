package com.example.departmentservice

import com.example.departmentservice.entity.DepartmentEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DepartmentRepository : CrudRepository<DepartmentEntity, UUID> {
    fun existsByName(name: String): Boolean

    @Query("SELECT COUNT(d) <= 0 FROM DepartmentEntity d WHERE d.id = ?1")
    fun notExistsById(id: UUID): Boolean
}
