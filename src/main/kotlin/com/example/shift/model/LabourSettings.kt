package com.example.shift.model

data class LabourSettings(
        var autoBreak: Boolean,
        var dailyOvertimeMultiplier: Double,
        var dailyOvertimeThreshold: Long,
        var overtime: Boolean,
        var weeklyOvertimeMultiplier: Double,
        var weeklyOvertimeThreshold: Long,
        var autoBreakRules: MutableCollection<AutoBreakRule>
)