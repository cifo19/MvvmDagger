package com.caferk.movies.di

import com.caferk.movies.di.scope.PerActivity
import com.caferk.movies.ui.detail.DetailActivity
import com.caferk.movies.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cafer on 11.4.2018.
 */
@Module
abstract class TestActivitiesModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(TestMainActivityModule::class)])
    abstract fun contributesMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(TestDetailActivityModule::class)])
    abstract fun contributesDetailActivity(): DetailActivity
}