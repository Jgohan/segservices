package com.example.departmentservice.mapper

import com.example.departmentservice.entity.DepartmentEntity
import com.example.departmentservice.model.Department
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class DepartmentMapper : ModelEntityMapper<Department, DepartmentEntity> {

    override fun toModel(entity: DepartmentEntity) =
        Department(
            id = entity.id,
            name = entity.name
        )

    override fun toEntity(model: Department) =
        DepartmentEntity(name = model.name)

    fun toEntity(model: Department, id: UUID? = null) =
        DepartmentEntity(
            id = id ?: UUID.randomUUID(),
            name =  model.name
        )

}