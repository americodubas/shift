package com.example.shift.model

import java.util.*

data class User(
        var id: Long,
        var active: Boolean,
        var created: Date,
        var modified: Date,
        var locationId: Long,
        var userType: Int,
        var email: String,
        var firstName: String,
        var lastName: String ,
        var hourlyWage: Double,
        var photo: String
)