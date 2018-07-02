package com.example.shift.service

import com.example.shift.model.User
import com.example.shift.util.jsonMapToMapString
import java.net.URL

fun getUsers(): List<User> {
    val json = URL("https://shiftstestapi.firebaseio.com/users.json").readText()
    val text = jsonMapToMapString(json)
    System.out.println(text.size)
    System.out.println(text.keys)
    System.out.println(text.values)
    val users = mutableListOf<User>()
    text.values.forEach {
//        users.add(jsonToObject(it))
        System.out.println(it)
    }
    System.out.println(users.size)
//    val users = jsonListToObject<User>(text[0])
//    users.forEach {
//        System.out.println("Name: ${it.firstName}")
//    }
    return emptyList()
}