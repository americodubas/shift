package com.example.shift.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.*

/**
 * Convert date to instant and create a local date from it.
 * Adjust the local date to the previous monday
 */
fun getFirstDayOfTheWeek(d: Date): LocalDate? {
    return LocalDate.ofInstant(d.toInstant(), ZoneId.systemDefault()).with(TemporalAdjusters.previous(DayOfWeek.MONDAY))
}