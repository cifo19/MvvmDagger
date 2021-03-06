package com.caferk.movies.ui.main

import com.caferk.movies.di.scope.PerFragment
import com.caferk.movies.ui.main.popular.AbstractFRPopularMoviesModule
import com.caferk.movies.ui.main.popular.FRPopularMoviesModule
import com.caferk.movies.ui.main.popular.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cafer.kaya on 14.03.2018.
 */
@Module
abstract class MainActivityModule {
    @PerFragment
    @ContributesAndroidInjector(modules = [(AbstractFRPopularMoviesModule::class), (FRPopularMoviesModule::class)])
    abstract fun contributePopularMoviesFragment(): PopularMoviesFragment
}