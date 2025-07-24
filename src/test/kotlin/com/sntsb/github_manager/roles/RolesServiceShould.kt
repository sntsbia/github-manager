package com.sntsb.github_manager.roles

import com.sntsb.github_manager.roles.model.Roles
import com.sntsb.github_manager.roles.repository.RolesRepository
import com.sntsb.github_manager.users.model.Users
import com.sntsb.github_manager.users.repository.UsersRepository
import com.sntsb.github_manager.users.service.UsersService
import com.sntsb.github_manager.utils.BaseServiceShould
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class RolesServiceShould : BaseServiceShould() {

    @MockK
    private lateinit var userRepository: UsersRepository

    @MockK
    private lateinit var profileRepository: RolesRepository

    @InjectMockKs
    private lateinit var userService: UsersService

    @Test
    fun getAllUsersWithRolesSuccessfully() {

        val users = Users(id = 1L, login = "testuser")
        val roles = Roles(id = 10L, name = "Developer")
        val userSlot = slot<Users>()

        every { userRepository.findById(1L) } returns Optional.of(users)
        every { profileRepository.findById(10L) } returns Optional.of(roles)
        every { userRepository.save(capture(userSlot)) } returns users

        val updatedUser = userService.assignRoles(userId = 1L, rolesId = 10L)

        verify(exactly = 1) { userRepository.save<Users>(any()) }

        assertEquals("testuser", updatedUser.getOrNull()?.login)
        assertEquals(1, updatedUser.getOrNull()?.roles?.size)
        assertTrue(updatedUser.getOrNull()?.roles?.contains(roles) == true)

        assertTrue(userSlot.captured.roles.contains(roles))
    }

    @Test
    fun emitExceptionIfUserNotExists() {
        every { userRepository.findById(any()) } returns Optional.empty()

        val exception =
            userService.assignRoles(userId = 999L, rolesId = 10L)

        assertEquals("Usuário não encontrado", exception.exceptionOrNull()?.message)

        verify(exactly = 0) { profileRepository.findById(any()) }
        verify(exactly = 0) { userRepository.save(any()) }
    }

}
