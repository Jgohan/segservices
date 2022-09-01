package com.example.departmentservice.model

import com.example.departmentservice.entity.DepartmentEntity
import java.util.*

data class Department(
    val id: UUID?,
    val name: String
) {
    fun toEntity(id: UUID? = null) =
        DepartmentEntity(
            id = id ?: UUID.randomUUID(),
            name = name
        )
}