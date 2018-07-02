package com.example.shift.service

import com.example.shift.model.Location
import org.junit.Assert
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
        Assert.assertEquals(1, locations.size)
    }

    @Test
    fun locationShouldBeSaoPaulo() {
        Assert.assertEquals("São Paulo", locations.first().city)
    }
}