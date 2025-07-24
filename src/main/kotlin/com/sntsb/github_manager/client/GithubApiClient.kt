package com.sntsb.github_manager.client

import com.sntsb.github_manager.client.model.GithubUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "github", url = "https://api.github.com")
interface GithubApiClient {
    @GetMapping("/users")
    fun getUsers(@RequestParam("per_page") count: Int): List<GithubUserResponse>
}
