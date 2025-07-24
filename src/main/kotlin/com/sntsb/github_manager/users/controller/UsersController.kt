package com.sntsb.github_manager.users.controller

import com.sntsb.github_manager.users.model.dto.AssignRolesRequest
import com.sntsb.github_manager.users.model.dto.CreateUsersRequest
import com.sntsb.github_manager.users.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(
    private val usersService: UsersService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userRequest: CreateUsersRequest): ResponseEntity<Any> {
        println("Criando um novo usuário com o nome: ${userRequest.login}")

        return usersService.create(userRequest.login).fold(
            onSuccess = { newUser ->
                ResponseEntity.status(HttpStatus.CREATED).body(newUser)
            },
            onFailure = { exception ->
                val errorMessage = exception.message ?: "Ocorreu um erro desconhecido"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to errorMessage))
            }
        )
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): ResponseEntity<Any> {
        println("Buscando todos os usuários")

        return usersService.getAll().fold(
            onSuccess = {usersList ->
                ResponseEntity.status(HttpStatus.OK).body(usersList)
            },
            onFailure = {exception ->
                val errorMessage = exception.message ?: "Ocorreu um erro desconhecido"

                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to errorMessage))
            }
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/roles") // Defina o placeholder aqui
    fun assignRole(@PathVariable("userId") userId: Long, @RequestBody request: AssignRolesRequest): ResponseEntity<Any> {

        println("Adicionando a role ${request.rolesId} para o usuário${userId}")

        return usersService.assignRoles(userId, request.rolesId).fold(
            onSuccess = { newUser ->
                ResponseEntity.status(HttpStatus.CREATED).body(newUser)
            },
            onFailure = { exception ->
                val errorMessage = exception.message ?: "Ocorreu um erro desconhecido"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to errorMessage))
            }
        )
    }


}
