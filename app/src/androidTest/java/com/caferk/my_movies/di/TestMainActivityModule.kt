package com.caferk.my_movies.di

import com.caferk.my_movies.di.scope.PerFragment
import com.caferk.my_movies.ui.main.popular.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cafer on 11.4.2018.
 */
@Module
abstract class TestMainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [(TestFRPopularMoviesModule::class)])
    abstract fun contributePopularMoviesFragment(): PopularMoviesFragment
}