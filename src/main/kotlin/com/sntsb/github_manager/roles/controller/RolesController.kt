package com.sntsb.github_manager.roles.controller

import com.sntsb.github_manager.roles.model.Roles
import com.sntsb.github_manager.roles.model.dto.CreateRolesRequest
import com.sntsb.github_manager.roles.service.RolesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/roles")
class RolesController(private val rolesService: RolesService) {

    @PostMapping
    fun createRole(@RequestBody roleRequest: CreateRolesRequest): ResponseEntity<Roles> {

        println("Criando uma nova role com o nome: ${roleRequest.name}")

        val newRoleResult = rolesService.create(roleRequest.name)

        if(newRoleResult.isFailure){
            newRoleResult.exceptionOrNull()?.let{
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it.message)
            }
        } else{
            newRoleResult.getOrNull()?.let{ newUser->
                ResponseEntity.status(HttpStatus.CREATED).body(newUser)
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)

    }
}
