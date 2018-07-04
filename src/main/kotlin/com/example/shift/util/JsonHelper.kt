package com.example.shift.util

import com.example.shift.model.Location
import com.example.shift.model.TimePunch
import com.example.shift.model.User
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

val builder: Gson = GsonBuilder()
        .registerTypeAdapter(Boolean::class.java, BooleanDeserializer()) //solve problem with booleans [1,true,0,false]
        .setDateFormat("yyyy-MM-dd HH:mm:ss") //date format from json
        .setPrettyPrinting()
        .create()

fun jsonMapToUserMap(json: String): HashMap<String, HashMap<String, User>> {
    return builder.fromJson(json, object: TypeToken<HashMap<String, HashMap<String, User>>>() {}.type)
}

fun jsonMapToLocationMap(json: String): HashMap<String, Location> {
    return builder.fromJson(json, object: TypeToken<HashMap<String, Location>>() {}.type)
}

fun jsonMapToTimePunchMap(json: String): HashMap<String, TimePunch> {
    return builder.fromJson(json, object: TypeToken<HashMap<String, TimePunch>>() {}.type)
}

/**
 * Deserialize String to Boolean
 * '1' || 'true' -> true
 * '0' || 'false' -> false
 */
class BooleanDeserializer : JsonDeserializer<Boolean> {
    @Throws(JsonParseException::class)
    @Override
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Boolean {
        val s = json.asJsonPrimitive.asString
        return when(s) {
            "true" -> true
            "1" -> true
            else -> false
        }
    }
}