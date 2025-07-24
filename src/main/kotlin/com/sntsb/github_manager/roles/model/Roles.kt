package com.sntsb.github_manager.roles.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "roles")
class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(unique = true, nullable = false)
    var name: String = ""

    constructor(name: String) {
        this.name = name
    }

    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
    }

}