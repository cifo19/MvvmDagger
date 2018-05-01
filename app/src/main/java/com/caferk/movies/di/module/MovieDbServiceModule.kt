package com.caferk.movies.di.module

import com.caferk.movies.model.RestApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by cafer on 14.2.2018.
 */
@Module(includes = [(NetworkModule::class)])
open class MovieDbServiceModule {

    @Provides
    @Singleton
    fun getGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun getGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create())
    }

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/")
                .build()
    }

    @Provides
    @Singleton
    fun getMovieDbService(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }
}