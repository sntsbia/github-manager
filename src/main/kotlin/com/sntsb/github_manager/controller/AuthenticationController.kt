package com.sntsb.github_manager.controller

import com.sntsb.github_manager.controller.dto.LoginRequest
import com.sntsb.github_manager.controller.dto.LoginResponse
import com.sntsb.github_manager.util.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(

    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil

) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )

        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)

        val token = jwtUtil.generateToken(userDetails)

        return ResponseEntity.ok(LoginResponse(token))
    }
}