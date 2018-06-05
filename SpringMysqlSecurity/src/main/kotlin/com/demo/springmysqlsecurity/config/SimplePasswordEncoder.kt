package com.demo.springmysqlsecurity.config

import org.springframework.security.crypto.password.PasswordEncoder

class SimplePasswordEncoder:PasswordEncoder {
    override fun encode(rawPassword: CharSequence?): String {
        return rawPassword.toString()
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return true
    }
}