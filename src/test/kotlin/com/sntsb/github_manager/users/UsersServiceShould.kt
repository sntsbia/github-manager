package com.sntsb.github_manager.users

import com.sntsb.github_manager.roles.model.Roles
import com.sntsb.github_manager.roles.repository.RolesRepository
import com.sntsb.github_manager.roles.service.RolesService
import com.sntsb.github_manager.utils.BaseServiceShould
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class UsersServiceShould : BaseServiceShould() {

    @MockK
    private lateinit var rolesRepository: RolesRepository

    @InjectMockKs
    private lateinit var rolesService: RolesService

    @Test
    fun createRoleSuccessfully() {
        val roleName = "ADMIN"
        val roleSalva = Roles(id = 1L, name = roleName)

        every { rolesRepository.findById(1) } returns Optional.ofNullable(null)
        every { rolesRepository.save(any()) } returns roleSalva

        val result = rolesService.create(roleName)

        assertTrue(result.isSuccess)
        assertEquals(roleSalva, result.getOrNull())

        verify(exactly = 1) { rolesRepository.save(any()) }
    }

}