package com.example.shift.service

import org.junit.Test

import org.junit.Assert.*

class UserServiceKtTest {

    @Test
    fun getUsersShouldWork() {
        val users = getUsers()
        assertTrue(users.isNotEmpty())
    }
}