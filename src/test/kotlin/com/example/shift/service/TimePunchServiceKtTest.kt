package com.example.shift.service

import com.example.shift.model.TimePunch
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TimePunchServiceKtTest {

    lateinit var timePunches: MutableCollection<TimePunch>

    @Before
    fun init() {
        timePunches = getTimePunches()
    }

    @Test
    fun shouldNotBeEmpty() {
        assertTrue(timePunches.isNotEmpty())
    }

    @Test
    fun clockedInShouldNotBeNull() {
        assertNotNull(timePunches.first().clockedIn)
    }
}