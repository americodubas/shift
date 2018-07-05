package com.example.shift.service

import com.example.shift.model.Location
import com.example.shift.model.TimePunch
import com.example.shift.model.User
import com.example.shift.util.getFirstDayOfTheWeek
import com.example.shift.util.jsonMapToUserMap
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

const val userUrl = "https://shiftstestapi.firebaseio.com/users.json"

/**
 * Get users from the URL, convert json to a map and return the values
 * The json structure is a map(id, map(userId, user))
 */
fun getUsers() = jsonMapToUserMap( URL(userUrl).readText() ).values.first().values

fun calculateAllUsersTimePunches() {
    val users = getUsers()
    val locationsMap = getLocationsMap()
    val timePunches = getTimePunches()

    calculateUserTimePunches(users.first(), locationsMap, timePunches)
}

fun calculateUserTimePunches(user: User, locationsMap: HashMap<String, Location>, timePunches: MutableCollection<TimePunch>) {
    val userTimePunches = getUserTimePunchesOrdered(timePunches, user)

    userTimePunches.forEach {
        val location = locationsMap.get(it.locationId.toString())
        sumWorkedHours(it, user)
        sumDailyOvertime(it, user, location)
        sumWeeklyOvertime(it, user, location)
    }

    //convert sums to hours
    user.totalWorkedHours = TimeUnit.MILLISECONDS.toHours(user.totalWorkedHours)
    user.totalDailyOvertime = TimeUnit.MINUTES.toHours(user.totalDailyOvertime)
}

fun sumWeeklyOvertime(timePunch: TimePunch, user: User, location: Location?) {
    if (user.auxFirstDayOfTheWeek == null) {
        user.auxFirstDayOfTheWeek = getFirstDayOfTheWeek(timePunch.clockedIn)

    } else if (user.auxFirstDayOfTheWeek != getFirstDayOfTheWeek(timePunch.clockedIn)){
        
    }
    user.auxWeekMinutes += getWorkedMinutes(timePunch)
}

fun sumDailyOvertime(timePunch: TimePunch, user: User, location: Location?) {
    if (location == null) {
        return
    }
    val minutes = getWorkedMinutes(timePunch)

    if (minutes > location.labourSettings.dailyOvertimeThreshold) {
        user.totalDailyOvertime += minutes - location.labourSettings.dailyOvertimeThreshold
    }
}

private fun getWorkedMinutes(timePunch: TimePunch) = TimeUnit.MILLISECONDS.toMinutes(
        timePunch.clockedOut.time - timePunch.clockedIn.time)

fun sumWorkedHours(timePunch: TimePunch, user: User) {
    user.totalWorkedHours += timePunch.clockedOut.time - timePunch.clockedIn.time
}

private fun getUserTimePunchesOrdered(timePunches: MutableCollection<TimePunch>, user: User) =
        timePunches.filter { it.userId == user.id }.sortedBy { it.clockedIn }


fun getUserTimePunch() {
    val user = getUsers().first()
    val timePunches = getTimePunchesFromUserInOrder(user.id)
    val locations = getLocationsMap()
    var dailyOvertime: Long = 0
    var weeklyOvertime: Long = 0
    var totalMinutes: Long = 0
    var weekMinutes: Long = 0

    timePunches.forEach {

        val c = Calendar.getInstance()
        c.time = it.clockedIn

        println("clock: ${it.clockedIn} monday: ${getFirstDayOfTheWeek(it.clockedIn)}")

        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            val location = locations.get(it.locationId.toString())
            if (location != null && weekMinutes > location.labourSettings.weeklyOvertimeThreshold) {
                weeklyOvertime += weekMinutes - location.labourSettings.weeklyOvertimeThreshold
            }
            weekMinutes = 0
        }
        val minutes = TimeUnit.MILLISECONDS.toMinutes(it.clockedOut.time - it.clockedIn.time)
        totalMinutes += minutes
        weekMinutes += minutes
        val location = locations.get(it.locationId.toString())
        if (location != null && minutes > location.labourSettings.dailyOvertimeThreshold) {
            dailyOvertime += minutes - location.labourSettings.dailyOvertimeThreshold
        }
    }
    println(totalMinutes)
    println(dailyOvertime)
    println(weeklyOvertime)
}

