package com.sntsb.github_manager.roles.repository

import com.sntsb.github_manager.roles.model.Roles
import org.springframework.data.jpa.repository.JpaRepository

interface RolesRepository: JpaRepository<Roles, Long>