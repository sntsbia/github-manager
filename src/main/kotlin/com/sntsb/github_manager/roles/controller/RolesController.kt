package com.sntsb.github_manager.roles.controller

import com.sntsb.github_manager.roles.model.Roles
import com.sntsb.github_manager.roles.model.dto.CreateRolesRequest
import com.sntsb.github_manager.roles.service.RolesService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/roles")
class RolesController(private val rolesService: RolesService) {

    @PostMapping
    fun createRole(@RequestBody roleRequest: CreateRolesRequest): ResponseEntity<Roles?> {

        println("Criando uma nova role com o nome: ${roleRequest.name}")

        val newRole = rolesService.create(roleRequest.name)

        return ResponseEntity.status(HttpStatus.CREATED).body(newRole)
    }
}
