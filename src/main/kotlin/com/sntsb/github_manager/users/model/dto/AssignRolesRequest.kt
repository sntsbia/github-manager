package com.sntsb.github_manager.users.model.dto

import jakarta.validation.constraints.NotBlank

data class AssignRolesRequest(
    val rolesId: Long
)
