package com.caferk.my_movies.di

import com.caferk.my_movies.BaseTestApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestDemoModule {

    @Provides
    @Singleton
    fun provideAnyThing(context: BaseTestApplication): String {
        return if (!context.toString().isEmpty()) {
            "test Me!"
        } else {
            "test Me Empty !"
        }
    }

    @Provides
    @Singleton
    fun provideService1(services: Services): Service1 = services.service1!!

    @Provides
    @Singleton
    fun provideService2(services: Services): Service2 = services.service2!!
}