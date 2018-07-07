package com.example.shift.service

import com.example.shift.model.Location
import com.example.shift.model.TimePunch
import com.example.shift.model.User
import com.example.shift.util.*
import java.net.URL
import java.time.LocalDate
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

const val userUrl = "https://shiftstestapi.firebaseio.com/users.json"

/**
 * Get users from the URL, convert json to a map and return the values
 * The json structure is a map(id, map(userId, user))
 */
fun getUsers() = jsonMapToUserMap( URL(userUrl).readText() ).values.first().values

fun getUsersWithCalculatedWorkedHours(): MutableCollection<User> {
    val users = getUsers()
    val locationsMap = getLocationsMap()
    val timePunches = getTimePunches()

    users.forEach {
        calculateUserTimePunches(it, locationsMap, timePunches)
    }

    return users
}

fun calculateUserTimePunches(user: User, locationsMap: HashMap<String, Location>, timePunches: MutableCollection<TimePunch>) {
    val userTimePunches = getUserTimePunchesOrdered(timePunches, user)
    val size = userTimePunches.size
    var count = 0

    userTimePunches.forEach {
        val location = locationsMap[it.locationId.toString()]
        sumWorkedHours(it, user)
        sumDailyOvertime(it, user, location)
        sumWeeklyOvertime(it, user, location, count + 1 == size)
        count++

//        println("in ${it.clockedIn} out ${it.clockedOut} work ${it.getWorkedMinutes()} user: ${user.totalWorkedHours} daily ${user.totalDailyOvertime} week ${user.totalWeeklyOvertime} aux ${user.auxWeekMinutes} last day ${user.auxLastDayOfTheWeek}")

    }

    //convert sums to hours
    user.totalWorkedHours = TimeUnit.MINUTES.toHours(user.totalWorkedHours)
    user.totalDailyOvertime = TimeUnit.MINUTES.toHours(user.totalDailyOvertime)
    user.totalWeeklyOvertime = TimeUnit.MINUTES.toHours(user.totalWeeklyOvertime)
}

/**
 * Create this method to skip empty clocked out dates that came on the json
 */
fun isValidRegister(it: TimePunch): Boolean {
    return it.clockedOut.toLocalDate() > LocalDate.of(2000,1,1)
}

fun sumWeeklyOvertime(timePunch: TimePunch, user: User, location: Location?, last: Boolean) {

    if (isFirstRegister(user) && isSunday(timePunch.clockedIn)) {
        //since it is the last day of the week and the first register we don't have to check the weekly overtime
        return
    }

    if (isFirstRegister(user)) {
        //set the last day of the week to compare later and know when to check the weekly overtime
        user.auxLastDayOfTheWeek = getLastDayOfTheWeek(timePunch.clockedIn)
    }

    if (timePunch.clockedIn.toLocalDate() > user.auxLastDayOfTheWeek || last) {
        if (user.auxWeekMinutes > toLong(location?.labourSettings?.weeklyOvertimeThreshold)) {
            user.totalWeeklyOvertime += user.auxWeekMinutes - toLong(location?.labourSettings?.weeklyOvertimeThreshold)
        }
        user.auxLastDayOfTheWeek = getLastDayOfTheWeek(timePunch.clockedIn)
        user.auxWeekMinutes = 0
    }

    user.auxWeekMinutes += timePunch.getWorkedMinutes()
}

private fun isFirstRegister(user: User) = user.auxLastDayOfTheWeek == null

fun sumDailyOvertime(timePunch: TimePunch, user: User, location: Location?) {
    val minutes = timePunch.getWorkedMinutes()

    if (minutes > toLong(location?.labourSettings?.dailyOvertimeThreshold)) {
        user.totalDailyOvertime += minutes - toLong(location?.labourSettings?.dailyOvertimeThreshold)
    }
}

fun sumWorkedHours(timePunch: TimePunch, user: User) {
    user.totalWorkedHours += timePunch.getWorkedMinutes()
}

private fun getUserTimePunchesOrdered(timePunches: MutableCollection<TimePunch>, user: User) =
        timePunches.filter { it.userId == user.id  && isValidRegister(it)}.sortedBy { it.clockedIn }