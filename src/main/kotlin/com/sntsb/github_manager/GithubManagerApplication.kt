package com.sntsb.github_manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class GithubManagerApplication

fun main(args: Array<String>) {
	runApplication<GithubManagerApplication>(*args)
}
