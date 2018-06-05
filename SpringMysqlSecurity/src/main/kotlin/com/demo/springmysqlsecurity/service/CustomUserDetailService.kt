package com.demo.springmysqlsecurity.service

import com.demo.springmysqlsecurity.model.CustomUserDetail
import com.demo.springmysqlsecurity.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

// implements UserDetailsService interface of spring security
// this class will be used by Spring for authorization
@Service
class CustomUserDetailService(@Autowired
                              private var userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        return username?.let { it: String ->

            var user: CustomUserDetail? = userRepository.findByName(username = it)

            return user?.let(block = ::CustomUserDetail) ?: run {
                throw UsernameNotFoundException("User name is not found for name:" + username)
            }
            // above is obtimized version of this implementation with lambda expression
//            return user?.let { it: CustomUserDetail ->
//                return@let CustomUserDetail(it)
//            }?: run {
//                throw UsernameNotFoundException("User name is not found for name:" + username)
//            }

        }?:run {
            throw org.springframework.security.core.userdetails.UsernameNotFoundException("User name is null")
        }
    }
}