package com.sntsb.github_manager.users.model

import com.sntsb.github_manager.roles.model.Roles
import jakarta.persistence.*

@Entity
@Table(name = "users")
class Users {
    @Id
    var id: Long = 0

    @Column(nullable = false)
    var login: String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "users_id")],
        inverseJoinColumns = [JoinColumn(name = "roles_id")]
    )
    var roles: MutableSet<Roles> = mutableSetOf()

    constructor(name: String) {
        this.login = name
    }

    constructor(id: Long, name: String) {
        this.id = id
        this.login = name
    }

}
