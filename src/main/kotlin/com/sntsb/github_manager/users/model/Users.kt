package com.sntsb.github_manager.users.model

import com.sntsb.github_manager.roles.model.Roles
import jakarta.persistence.*

@Entity
@Table(name = "users")
class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var login: String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
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
