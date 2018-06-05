package com.demo.springmysqlsecurity.config

import com.demo.springmysqlsecurity.repository.UserRepository
import com.demo.springmysqlsecurity.service.CustomUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = [UserRepository::class]) // as we are having only 1 Repository class.
@Configuration
class SecurityConfiguration(@Autowired
                            val userDetailsService: CustomUserDetailService) : WebSecurityConfigurerAdapter() {

    val passwordEncoder:SimplePasswordEncoder = SimplePasswordEncoder()

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {

        http?.let {
            it.csrf().disable() // disabled all csrf reference.

            it.authorizeRequests()
                    .antMatchers("**/secured/**").authenticated() // any request containing secured will be authenticated
                    .anyRequest().permitAll() // any request with secured is allowed.
                    .and()
                    .formLogin().permitAll() // all authorized requests will go to spring login page, if not authorized.
//                .formLogin().loginPage("login page").permitAll() // if needed custom login page.
        }


    }
}