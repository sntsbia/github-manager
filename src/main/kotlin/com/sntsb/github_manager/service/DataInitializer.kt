package com.sntsb.github_manager.service

import com.sntsb.github_manager.client.GithubApiClient
import com.sntsb.github_manager.users.model.Users
import com.sntsb.github_manager.users.repository.UsersRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val githubApiClient: GithubApiClient,
    private val userRepository: UsersRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (userRepository.count() == 0L) {
            println("Populando banco com usuarios do GitHub...")
            try {
                val usersFromApi = githubApiClient.getUsers(count = 30)
                val usersToSave = usersFromApi.map {
                    Users(id = it.id, login = it.login)
                }
                userRepository.saveAll(usersToSave)

                println("${usersToSave.size} usuários salvos.")
            } catch (e: Exception) {
                println("Erro ao buscar usuários do GitHub: ${e.message}")
            }
        }
    }
}
