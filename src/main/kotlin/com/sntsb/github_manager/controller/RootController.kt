package com.sntsb.github_manager.controller

import com.sntsb.github_manager.controller.dto.ApiEndpoint
import com.sntsb.github_manager.controller.dto.WelcomeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class RootController {

    @GetMapping
    fun getRoot(): WelcomeResponse {
        val publicEndpoints = listOf(
            ApiEndpoint("POST", "/auth/login", "Gera um token de autenticação JWT."),
            ApiEndpoint("GET", "/h2-console", "Acessa o console do banco de dados em memória.")
        )

        val protectedEndpoints = listOf(
            ApiEndpoint("GET", "/users", "Lista todos os usuários e suas roles vinculadas."),
            ApiEndpoint("POST", "/users", "Cria um novo usuário"),
            ApiEndpoint("POST", "/roles", "Cria uma nova role (ex: 'Developer')."),
            ApiEndpoint("POST", "/users/{userId}/roles", "Vincula uma role a um usuário específico.")
        )

        return WelcomeResponse(
            message = "Bem-vindo à API de Gerenciamento de Usuários do GitHub!",
            publicEndpoints = publicEndpoints,
            protectedEndpoints = protectedEndpoints
        )
    }
}