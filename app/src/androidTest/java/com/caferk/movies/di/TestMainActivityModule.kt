package com.caferk.movies.di

import com.caferk.movies.di.scope.PerFragment
import com.caferk.movies.ui.main.popular.PopularMoviesFragment
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