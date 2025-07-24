package com.sntsb.github_manager.roles.service

import com.sntsb.github_manager.roles.model.Roles
import com.sntsb.github_manager.roles.repository.RolesRepository
import org.springframework.stereotype.Service

@Service
class RolesService(
    private val rolesRepository: RolesRepository
) {
    fun create(rolesName: String): Roles? {
        val newRoles = Roles(rolesName)

        return rolesRepository.save<Roles?>(newRoles)
    }
}