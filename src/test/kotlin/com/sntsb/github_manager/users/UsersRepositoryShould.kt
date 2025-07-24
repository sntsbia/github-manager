package com.sntsb.github_manager.users

import com.sntsb.github_manager.users.model.dto.CreateUsersRequest
import com.sntsb.github_manager.utils.BaseControllerShould
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.post
import kotlin.test.Test

class UsersRepositoryShould : BaseControllerShould() {

    @Test
    @WithMockUser
    fun createNewUser() {

        val newUserRequest = CreateUsersRequest(name = "User Test")

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

}
