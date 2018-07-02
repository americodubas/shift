package com.example.shift.model

import java.util.*

data class Location (
        var id: Long,
        var address: String,
        var city: String,
        var country: String,
        var created: Date,
        var lat: Float,
        var lng: Float,
        var modified: Date,
        var state: String,
        var timeZone: String
)