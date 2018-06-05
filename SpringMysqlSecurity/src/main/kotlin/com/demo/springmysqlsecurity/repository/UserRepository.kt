package com.demo.springmysqlsecurity.repository

import com.demo.springmysqlsecurity.model.CustomUserDetail
import org.springframework.data.jpa.repository.JpaRepository

    public interface UserRepository:JpaRepository<CustomUserDetail, Int>{

    fun findByName(username: String): CustomUserDetail?

}