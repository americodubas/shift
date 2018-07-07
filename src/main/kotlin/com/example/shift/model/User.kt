package com.example.shift.model

import java.util.*
import java.time.LocalDate

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
        var photo: String,
        var totalWorkedHours: Long = 0,
        var totalRegularHours: Long = 0,
        var totalDailyOvertime: Long = 0,
        var totalWeeklyOvertime: Long = 0,
        var auxWeekMinutes: Long = 0,
        var auxLastDayOfTheWeek: LocalDate? = null
)