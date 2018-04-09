package com.caferk.my_movies.di.module

import com.caferk.my_movies.di.scope.PerActivity
import com.caferk.my_movies.ui.detail.DetailActivity
import com.caferk.my_movies.ui.detail.DetailActivityModule
import com.caferk.my_movies.ui.main.MainActivity
import com.caferk.my_movies.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cafer on 2.4.2018.
 */
@Module
abstract class ActivitiesModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun contributesMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(DetailActivityModule::class)])
    abstract fun contributesDetailActivity(): DetailActivity
}