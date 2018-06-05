package com.demo.springmysqlsecurity.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "person")
data class CustomUserDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "user_id")
        var id: Int = 0,

        @Column(name = "email")
        var email: String = "",

        @Column(name = "password")
        var userPassword: String = "",

        @Column(name = "name")
        var name: String = "",

        @Column(name = "last_name")
        var lastName: String = "",

        @Column(name = "active")
        var active: String = "",

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinTable(name = "user_role", joinColumns = [(JoinColumn(name = "user_id"))], inverseJoinColumns = [(JoinColumn(name = "role_id"))])
        var roles: Set<Role>? = null
) : UserDetails {

    lateinit var user: CustomUserDetail

    val prefixRole:String = "ROLE_" // CASE SENSITIVE.

    override fun getUsername(): String = this.name

    override fun getPassword(): String {
        return this.userPassword
    }

    @Throws
    override fun getAuthorities(): LinkedList<GrantedAuthority>? {

        var authorities: LinkedList<GrantedAuthority>? = this.user.let {
            it.roles.let {
                it!!.mapTo(destination = LinkedList<GrantedAuthority>()) {
                    return@mapTo SimpleGrantedAuthority(prefixRole + it.role);
                }
            }
        }

        return authorities;
    }


    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true


    constructor(user1: CustomUserDetail) : this() {
        this.id = user1.id
        this.active = user1.active
        this.email = user1.email
        this.lastName = user1.lastName
        this.userPassword = user1.userPassword
        this.name = user1.name
        this.roles = user1.roles;
        this.user = user1
    }


}