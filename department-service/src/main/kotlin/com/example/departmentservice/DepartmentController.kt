package com.example.departmentservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/departments")
class DepartmentController(
    @Autowired val departmentService: DepartmentService
) {

    @PostMapping
    fun createDepartment(@RequestBody name: String ) =
        departmentService.createDepartment(name)

    @GetMapping
    fun getDepartments() =
        departmentService.getDepartments()

    @PutMapping("/{name}")
    fun updateDepartment(
        @PathVariable name: String,
        @RequestBody newName: String
    ) =
        departmentService.updateDepartment(name, newName)

    @DeleteMapping("/{name}")
    fun deleteDepartments(@PathVariable name: String) =
        departmentService.deleteDepartments(name)

}