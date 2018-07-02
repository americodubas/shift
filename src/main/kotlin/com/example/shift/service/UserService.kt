package com.example.shift.service

import com.example.shift.util.jsonMapToUserMap
import java.net.URL

const val userUrl = "https://shiftstestapi.firebaseio.com/users.json"

/**
 * Get users from the URL, convert json to a map and return the values
 * The json structure is a map(id, map(userId, user))
 */
fun getUsers() = jsonMapToUserMap( URL(userUrl).readText() ).values.first().values