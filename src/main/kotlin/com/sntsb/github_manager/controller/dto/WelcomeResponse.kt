package com.sntsb.github_manager.controller.dto

data class ApiEndpoint(val method: String, val path: String, val description: String)

data class WelcomeResponse(
    val message: String,
    val publicEndpoints: List<ApiEndpoint>,
    val protectedEndpoints: List<ApiEndpoint>
)