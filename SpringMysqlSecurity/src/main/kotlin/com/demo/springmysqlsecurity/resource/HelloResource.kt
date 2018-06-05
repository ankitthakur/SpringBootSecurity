package com.demo.springmysqlsecurity.resource

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/rest/hello")
@RestController
class HelloResource {

    @GetMapping( "/all")
    public fun hello(): String{
        return  "Hello sample"
    }

    @PreAuthorize("hasAnyRole('ADMIN')") // this rest point is now only allowed to Admin role.
    @GetMapping("/secured/all") // SecurityConfiguration will check "secured" in query and will authorize.
    public fun securedHello(): String {
        return "Hello secured sample"
    }

    @GetMapping("/secured/alternate") // SecurityConfiguration will check "secured" in query and will authorize.
    public fun securedAlternate(): String {
        return "Hello secured alternate"
    }

}
