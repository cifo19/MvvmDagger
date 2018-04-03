package com.caferk.my_movies.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.caferk.my_movies.ui.movie.popular.PopularMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by cafer on 3.4.2018.
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    internal abstract fun mainViewModel(viewModel: PopularMoviesViewModel): ViewModel

    //Add more ViewModels here
}