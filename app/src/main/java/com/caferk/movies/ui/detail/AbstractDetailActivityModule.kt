package com.caferk.movies.ui.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.caferk.movies.di.scope.PerActivity
import com.caferk.movies.ui.base.BaseActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AbstractDetailActivityModule {

    @Binds
    abstract fun bindDetailActivity(detailActivity: DetailActivity): BaseActivity
}

@Module
object DetailActivityModule {

    @Provides
    @JvmStatic
    @PerActivity
    fun provideDetailViewModel(
        baseActivity: BaseActivity,
        viewModelFactory: ViewModelProvider.Factory
    ): DetailViewModel {
        return ViewModelProviders.of(baseActivity, viewModelFactory)[DetailViewModel::class.java]
    }
}