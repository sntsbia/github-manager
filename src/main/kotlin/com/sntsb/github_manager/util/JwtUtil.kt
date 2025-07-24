package com.sntsb.github_manager.util

import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {
    @Value("\${jwt.secret}")
    private lateinit var secretString: String


    private val secretKey: SecretKey by lazy {
        Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretString))
    }

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }


    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = mapOf<String, Any>()
        return createToken(claims, userDetails.username)
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(secretKey)
            .compact()
    }

    private fun isTokenExpired(token: String): Boolean {
        val expirationDate = extractClaim(token, Claims::getExpiration)
        return expirationDate.before(Date())
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}