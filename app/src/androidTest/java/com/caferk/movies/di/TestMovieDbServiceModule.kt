package com.caferk.movies.di

import com.caferk.movies.model.RestApi
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

/**
 * Created by cafer on 11.4.2018.
 */
@Module
class TestMovieDbServiceModule {

    @Provides
    @Singleton
    fun getMovieDbService(): RestApi = mock(RestApi::class.java)
}