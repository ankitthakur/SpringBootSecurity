package com.demo.springmysqlsecurity.model

import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                val roleId:Int = 0,

                @Column(name = "role")
                val role:String="") {
}