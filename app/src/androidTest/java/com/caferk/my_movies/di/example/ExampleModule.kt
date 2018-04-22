package com.caferk.my_movies.di.example

import com.caferk.my_movies.BaseTestApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by cafer on 13.4.2018.
 */
@Module
class ExampleModule {

    @Provides
    @Singleton
    fun provideExample(context: BaseTestApplication): String{
        return context.toString()
    }
}