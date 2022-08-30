package com.example.departmentservice

import org.springframework.stereotype.Service

@Service
class DepartmentService {

    private val departments = mutableListOf<String>()

    fun createDepartment(name: String ) =
        if (departments.contains(name)) "Department $name already exists"
        else departments.add(name).let { "Department $name created" }

    fun getDepartments() = departments.toString()

    fun updateDepartment(oldName: String, newName: String ) =
        if (departments.contains(oldName)) {
            val index = departments.indexOf(oldName)
            departments[index] = newName
            "Department $oldName was updated to $newName"
        }
        else "Department $oldName wasn't found"

    fun deleteDepartments(name: String) =
        if (departments.remove(name)) "Department $name was deleted"
        else "Department $name wasn't found"

}