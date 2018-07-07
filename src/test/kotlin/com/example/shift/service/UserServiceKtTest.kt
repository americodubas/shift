package com.example.shift.service

import com.example.shift.model.User
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserServiceKtTest {

    lateinit var users: MutableCollection<User>

    @Before
    fun init() {
        users = getUsers()
    }

    @Test
    fun shouldNotBeEmpty() {
        assertTrue(users.isNotEmpty())
    }

    @Test
    fun shouldHaveTwentySevenUsers() {
        assertEquals(27, users.size)
    }

    @Test
    fun shouldBeAbleToGetUserName() {
        assertTrue(users.first().firstName.isNotEmpty())
    }

    @Test
    fun userTimePunch() {
        calculateUserTimePunches(getUsers().find { it.id == 517135L }!!, getLocationsMap(), getTimePunches())
    }

}