package com.example.shift.service

import com.example.shift.model.Location
import com.example.shift.util.jsonMapToLocationMap
import com.example.shift.util.jsonMapToUserMap
import java.net.URL

const val locationUrl = "https://shiftstestapi.firebaseio.com/locations.json"

/**
 * Get locations from the URL, convert json to a map and return the values
 * The json structure is a map(locationId, location)
 */
fun getLocations() = getLocationsMap().values

fun getLocationsMap() = jsonMapToLocationMap(URL(locationUrl).readText())