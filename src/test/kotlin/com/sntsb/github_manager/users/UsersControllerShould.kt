package com.sntsb.github_manager.users

import com.sntsb.github_manager.users.model.Users
import com.sntsb.github_manager.users.model.dto.AssignRolesRequest
import com.sntsb.github_manager.users.model.dto.CreateUsersRequest
import com.sntsb.github_manager.users.repository.UsersRepository
import com.sntsb.github_manager.users.service.UsersService
import com.sntsb.github_manager.utils.BaseControllerShould
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import kotlin.test.Test

class UsersControllerShould : BaseControllerShould() {

    @Autowired
    private lateinit var usersRepository: UsersRepository

    @Autowired
    private lateinit var usersService: UsersService


    @Test
    @WithMockUser
    fun createNewUser() {

        val newUserRequest = CreateUsersRequest(login = "User Test")

        mockMvc.post("/users") {
            with(csrf())
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newUserRequest)
        }.andExpect {
            status { isCreated() }
            jsonPath("$.id") { exists() }
            jsonPath("$.login") { value("User Test") }
        }
    }

    @Test
    @WithMockUser
    fun getAllUsers() {

        usersRepository.save(Users(name = "User Test"))

        mockMvc.get("/users") {
            with(csrf())
        }.andExpect {
            status { isOk() }
            jsonPath("$") { isArray() }
            jsonPath("$[0].login") { value("User Test") }
        }

    }

    @Test
    @WithMockUser
    fun assignRoleToUserSuccessfully() {

        val user = usersRepository.save(Users(name = "User Test"))
        val assignRequest = AssignRolesRequest(rolesId = 1L)

        mockMvc.post("/users/${user.id}/roles") {
            with(csrf())
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(assignRequest)
        }.andExpect {
            status { isCreated() }
            jsonPath("$.id") { value(user.id) }
            jsonPath("$.roles") { isArray() }
            jsonPath("$.roles[0].id") { value(assignRequest.rolesId) }
        }

    }

    @Test
    @WithMockUser
    fun getAllUsersWithRoles() {

        val user = usersRepository.save(Users(name = "User Test"))
        val assignRequest = AssignRolesRequest(rolesId = 1L)

        user.id?.let {

            usersService.assignRoles(it, 1L)
        }

        mockMvc.get("/users") {
            with(csrf())
        }.andExpect {
            status { isOk() }
            jsonPath("$") { isArray() }
            jsonPath("$[0].login") { value("User Test") }
            jsonPath("$[0].roles") { isArray() }
            jsonPath("$[0].roles[0].id") { value(assignRequest.rolesId) }
        }

    }


}
