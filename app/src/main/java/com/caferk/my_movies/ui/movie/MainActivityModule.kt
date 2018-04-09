package com.caferk.my_movies.ui.movie

import com.caferk.my_movies.di.scope.PerFragment
import com.caferk.my_movies.ui.movie.popular.FRPopularMoviesModule
import com.caferk.my_movies.ui.movie.popular.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cafer.kaya on 14.03.2018.
 */
@Module
abstract class MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [(FRPopularMoviesModule::class)])
    abstract fun contributePopularMoviesFragment(): PopularMoviesFragment

}