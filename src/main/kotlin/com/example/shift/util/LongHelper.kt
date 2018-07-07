package com.example.shift.util

/**
 * Check if the Long? is null and return 0
 * Else return the Long
 */
fun toLong(l: Long?): Long {
    if (l == null) {
        return 0
    }
    return l
}