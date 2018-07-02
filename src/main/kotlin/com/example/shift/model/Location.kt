package com.example.shift.model

import java.util.*

data class Location (
        var id: Long,
        var address: String,
        var city: String,
        var country: String,
        var created: Date,
        var lat: Double,
        var lng: Double,
        var modified: Date,
        var state: String,
        var timeZone: String,
        var labourSettings: LabourSettings
)