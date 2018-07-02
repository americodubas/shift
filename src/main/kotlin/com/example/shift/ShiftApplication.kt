package com.example.shift

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ShiftApplication

fun main(args: Array<String>) {
    SpringApplication.run(ShiftApplication::class.java, *args)
}
