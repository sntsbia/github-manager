package com.sntsb.github_manager.users.controller

import com.sntsb.github_manager.users.model.Users
import com.sntsb.github_manager.users.model.dto.CreateUsersRequest
import com.sntsb.github_manager.users.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(
    private val usersService: UsersService
) {
    @PostMapping
    fun createUser(@RequestBody userRequest: CreateUsersRequest): ResponseEntity<Users> {

        println("Criando um novo usuário com o nome: ${userRequest.name}")

        val newUser = usersService.create(userRequest.name)

        return newUser?.let {
            ResponseEntity.status(HttpStatus.CREATED).body(newUser)
        } ?: run {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }

    }
}
