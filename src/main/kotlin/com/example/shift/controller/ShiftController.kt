package com.example.shift.controller

import com.example.shift.service.getUsersWithCalculatedWorkedHours
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ShiftController {

    @GetMapping("/")
    fun welcome(model: Model): String {
        model.addAttribute("users", getUsersWithCalculatedWorkedHours())
        return "welcome"
    }

}