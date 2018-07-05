package com.example.shift.service

import com.example.shift.util.jsonMapToTimePunchMap
import java.net.URL

const val timePunchUrl = "https://shiftstestapi.firebaseio.com/timePunches.json"

/**
 * Get time punchas from the URL, convert json to a map and return the values
 * The json structure is a map(timePunchId, timePunch)
 */
fun getTimePunches() = jsonMapToTimePunchMap( URL(timePunchUrl).readText() ).values

fun getTimePunchesFromUserInOrder(userId: Long)
        = getTimePunches().filter { it.userId == userId }.sortedBy { it.clockedIn }
