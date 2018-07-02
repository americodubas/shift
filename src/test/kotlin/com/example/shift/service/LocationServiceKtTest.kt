package com.example.shift.service

import com.example.shift.model.Location
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LocationServiceKtTest {

    lateinit var locations: MutableCollection<Location>

    @Before
    fun init() {
        locations = getLocations()
    }

    @Test
    fun shouldNotBeEmpty() {
        assertTrue(locations.isNotEmpty())
    }

    @Test
    fun shouldHaveOneLocation() {
        assertEquals(1, locations.size)
    }

    @Test
    fun locationShouldBeSaoPaulo() {
        assertEquals("São Paulo", locations.first().city)
    }

    @Test
    fun overtimeShouldBeTrue() {
        assertTrue(locations.first().labourSettings.overtime)
    }

    @Test
    fun thresholdShouldBeFourHundredAndEighty() {
        assertEquals(480, locations.first().labourSettings.autoBreakRules.first().threshold)
    }
}