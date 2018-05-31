package com.caferk.movies.ui.main.popular

import android.arch.lifecycle.ViewModelProviders
import com.caferk.movies.di.viewmodel.ViewModelFactory
import com.caferk.movies.ui.base.BaseFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by cafer.kaya on 14.03.2018.
 */
@Module
abstract class AbstractFRPopularMoviesModule {
    @Binds
    abstract fun bindPopularMoviesFragment(popularMoviesFragment: PopularMoviesFragment): BaseFragment
}

@Module
object FRPopularMoviesModule {

    @JvmStatic
    @Provides
    fun providePopularMoviesViewModel(
        baseFragment: BaseFragment,
        viewModelFactory: ViewModelFactory
    ): PopularMoviesViewModel {
        return ViewModelProviders.of(
            baseFragment,
            viewModelFactory
        )[PopularMoviesViewModel::class.java]
    }
}