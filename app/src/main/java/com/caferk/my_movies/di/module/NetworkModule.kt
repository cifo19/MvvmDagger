package com.caferk.my_movies.di.module

import com.caferk.my_movies.BaseApplication
import com.caferk.my_movies.model.HttpInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import javax.inject.Singleton

/**
 * Created by cafer on 14.2.2018.
 */
@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun getFile(context: BaseApplication): File {
        return File(context.cacheDir, "okhttp-cache")
    }

    @Provides
    @Singleton
    fun getCache(file: File): Cache {
        return Cache(file, 10 * 1000 * 1000)
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor({ Timber.i(it) })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun getOkhttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor, httpInterceptor: HttpInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(httpInterceptor)
                .build()
    }
}