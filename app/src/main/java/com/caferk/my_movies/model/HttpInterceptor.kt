package com.caferk.my_movies.model

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by cafer on 2.4.2018.
 */
@Singleton
open class HttpInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val API_VERSION: Int = 3
        const val API_KEY: String = "35ef0461fc4557cf1d256d3335ed7545"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlBuilder: HttpUrl.Builder = originalRequest.url().newBuilder()

        urlBuilder.addPathSegment("forVersion")
        urlBuilder.setPathSegment(0, API_VERSION.toString())
        originalRequest.url().pathSegments().forEachIndexed({ index: Int, path: String? -> urlBuilder.setPathSegment(index + 1, path) })
        urlBuilder.addQueryParameter("api_key", API_KEY).build()

        val request = originalRequest.newBuilder()
                .url(urlBuilder.build())
                .build()

        return chain.proceed(request)
    }

}