package com.caferk.movies.util

/**
 * Created by cafer on 6.4.2018.
 */
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

val gsonUpper = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create()!!

inline fun <reified T> Gson.from(fileName: String): T {
    val type = object : TypeToken<T>() {}.type
    val resource = javaClass.classLoader.getResourceAsStream(fileName)
    val reader = BufferedReader(InputStreamReader(resource, "UTF-8"))
    return fromJson(reader, type)
}

inline infix fun <reified T> String.parseFileWith(gson: Gson): T = gson.from(this)

/*inline fun <reified T : BaseRestResponse> parseRootResponseFile(path: String): T {
    val resource = gsonUpper.javaClass.classLoader.getResourceAsStream(path)
    val reader = BufferedReader(InputStreamReader(resource, "UTF-8"))
    val response: RootResponse<T> = gsonUpper.fromJson(reader,
            RootResponseType(T::class.java)
    )
    return response.restResponse!!
}*/

/*
class RootResponseType(private val cls: Class<out Any>) : ParameterizedType {
    override fun getRawType(): Type = RootResponse::class.java

    override fun getOwnerType(): Type? = null

    override fun getActualTypeArguments(): Array<Type> = arrayOf(cls)
}*/
