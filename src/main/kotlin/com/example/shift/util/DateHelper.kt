package com.example.shift.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.*

/**
 * Adjust the local date to the next sunday
 */
fun getLastDayOfTheWeek(d: Date): LocalDate? {
    return d.toLocalDate().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
}

/**
 * Verify if it is sunday.
 */
fun isSunday(d: Date) = d.toLocalDate().dayOfWeek == DayOfWeek.SUNDAY

/**
 * Convert date to instant and create a local date from it.
 */
fun Date.toLocalDate(): LocalDate = LocalDate.ofInstant(this.toInstant(), ZoneId.systemDefault())