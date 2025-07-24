package com.sntsb.github_manager.users.service

import com.sntsb.github_manager.users.model.Users
import com.sntsb.github_manager.users.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository
) {

    fun create(usersName: String): Users? {

        val newUsers = Users(usersName)

        return usersRepository.save<Users?>(newUsers)

    }

}
