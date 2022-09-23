package com.example.departmentservice.mapper

import com.example.departmentservice.entity.AbstractEntity

interface ModelEntityMapper<M, E : AbstractEntity<*>> {
    fun toModel(entity: E): M
    fun toEntity(model: M): E
}
