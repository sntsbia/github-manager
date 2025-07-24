package com.sntsb.github_manager.roles

import com.sntsb.github_manager.roles.model.dto.CreateRolesRequest
import com.sntsb.github_manager.utils.BaseControllerShould
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.post
import kotlin.test.Test

class RolesControllerShould : BaseControllerShould() {

    @Test
    @WithMockUser
    fun createNewRole() {
        val newRoleRequest = CreateRolesRequest(name = "Test Engineer")

        mockMvc.post("/roles") {
            with(csrf())
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newRoleRequest)
        }.andExpect {
            status { isCreated() }
            jsonPath("$.id") { exists() }
            jsonPath("$.name") { value("Test Engineer") }
        }
    }
}
