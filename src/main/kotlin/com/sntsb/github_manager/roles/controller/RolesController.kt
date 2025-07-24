package com.sntsb.github_manager.roles.controller

import com.sntsb.github_manager.roles.model.dto.CreateRolesRequest
import com.sntsb.github_manager.roles.service.RolesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RolesController(private val rolesService: RolesService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRole(@RequestBody roleRequest: CreateRolesRequest): ResponseEntity<Any> {

        return rolesService.create(roleRequest.name).fold(

            onSuccess = { newRole ->
                ResponseEntity.status(HttpStatus.CREATED).body(newRole)
            },

            onFailure = { exception ->
                val errorMessage = exception.message ?: "Ocorreu um erro desconhecido"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to errorMessage))
            }
        )
    }
}
