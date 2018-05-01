package com.caferk.movies.di.module

import android.content.Context
import android.content.SharedPreferences
import com.caferk.movies.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by cafer on 14.2.2018.
 */
@Module
open class ApplicationModule {

    companion object {
        const val SHARED_PACKAGE = "base_shared_preferences"
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: BaseApplication): SharedPreferences {
        return context.getSharedPreferences(SHARED_PACKAGE, Context.MODE_PRIVATE)
    }
}