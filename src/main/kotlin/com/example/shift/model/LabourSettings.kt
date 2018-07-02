package com.example.shift.model

data class LabourSettings(
        var autoBreak: Boolean,
        var dailyOvertimeMultiplier: Double,
        var dailyOvertimeThreshold: Int,
        var overtime: Boolean,
        var weeklyOvertimeMultiplier: Double,
        var weeklyOvertimeThreshold: Int,
        var autoBreakRules: MutableCollection<AutoBreakRule>
)