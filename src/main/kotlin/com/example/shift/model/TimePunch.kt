package com.example.shift.model

import java.util.*

data class TimePunch (
        var clockedIn: Date,
        var clockedOut: Date,
        var created: Date,
        var hourlyWage: Double,
        var id: Long,
        var locationId: Long,
        var modified: Date,
        var userId: Long
) {

    fun getWorkedMilliseconds(): Long {
        return clockedOut.time - clockedIn.time
    }
}