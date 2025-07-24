package com.sntsb.github_manager.users.repository

import com.sntsb.github_manager.users.model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<Users, Long>
