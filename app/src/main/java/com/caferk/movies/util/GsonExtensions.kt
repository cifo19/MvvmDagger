package com.caferk.movies.util

/**
 * Created by cafer on 6.4.2018.
 */
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

val gson = GsonBuilder().create()!!

inline infix fun <reified T> Gson.from(fileName: String): T {
    val type = object : TypeToken<T>() {}.type
    val resource = javaClass.classLoader.getResourceAsStream(fileName)
    val reader = BufferedReader(InputStreamReader(resource, "UTF-8"))
    return fromJson(reader, type)
}
inline fun <reified T> String.parseFile(): T = gson from this