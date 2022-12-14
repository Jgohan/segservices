package com.example.departmentservice.entity

import com.example.departmentservice.model.Department
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "departments")
class DepartmentEntity(
    override val id: UUID = UUID.randomUUID(),
    val name: String
) : AbstractEntity<UUID>(id) {
    fun toModel() = Department(
        id = id,
        name = name
    )
}
