package com.caferk.movies.ui.main.popular

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.caferk.movies.di.scope.PerFragment
import com.caferk.movies.ui.base.BaseFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by cafer.kaya on 14.03.2018.
 */
@Module
abstract class AbstractFrPopularMoviesModule {
    @Binds
    abstract fun bindPopularMoviesFragment(popularMoviesFragment: PopularMoviesFragment): BaseFragment
}

@Module
object FrPopularMoviesModule {

    @Provides
    @JvmStatic
    @PerFragment
    fun providePopularMoviesViewModel(baseFragment: BaseFragment, viewModelFactory: ViewModelProvider.Factory): PopularMoviesViewModel {
        return ViewModelProviders.of(baseFragment, viewModelFactory)[PopularMoviesViewModel::class.java]
    }
}