package com.sntsb.github_manager.users.service

import com.sntsb.github_manager.roles.repository.RolesRepository
import com.sntsb.github_manager.users.model.Users
import com.sntsb.github_manager.users.repository.UsersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val rolesRepository: RolesRepository
) {

    fun create(usersName: String): Result<Users?> {

        val newUsers = Users(usersName)

        return Result.success(usersRepository.save<Users?>(newUsers))
    }

    fun getAll(): Result<List<Users>> {

        return Result.success(usersRepository.findAll())
    }

    @Transactional
    fun assignRoles(userId: Long, rolesId: Long): Result<Users?> {
        try {

            val users = usersRepository.findById(userId).orElseThrow { Exception("Usuário não encontrado") }
            val roles = rolesRepository.findById(rolesId).orElseThrow { Exception("Role não encontrada") }

            users.roles.add(roles)

            return Result.success(usersRepository.save<Users?>(users))
        } catch (exception: Exception) {
            return Result.failure(exception)
        }

    }

}
