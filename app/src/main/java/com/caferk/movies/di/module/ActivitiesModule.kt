package com.caferk.movies.di.module

import com.caferk.movies.di.scope.PerActivity
import com.caferk.movies.ui.detail.DetailActivity
import com.caferk.movies.ui.detail.AbstractDetailActivityModule
import com.caferk.movies.ui.detail.DetailActivityModule
import com.caferk.movies.ui.main.MainActivity
import com.caferk.movies.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cafer on 2.4.2018.
 */
@Module
abstract class ActivitiesModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [AbstractDetailActivityModule::class, DetailActivityModule::class])
    abstract fun contributesDetailActivity(): DetailActivity
}