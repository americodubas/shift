package com.example.shift.controller

import com.example.shift.service.getUsers
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ShiftController {

//    @RequestMapping(value = "/")
    @GetMapping("/")
    fun welcome(): String {
        System.out.println("here welcome!")
        val users = getUsers()
        System.out.println("size: ${users.size}")
        return "welcome"
    }

}