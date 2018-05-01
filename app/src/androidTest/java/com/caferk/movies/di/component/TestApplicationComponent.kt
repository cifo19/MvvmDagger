package com.caferk.movies.di.component

import com.caferk.movies.BaseTestApplication
import com.caferk.movies.PopularMoviesFragmentTest
import com.caferk.movies.di.TestActivitiesModule
import com.caferk.movies.di.TestMovieDbServiceModule
import com.caferk.movies.di.TestUIModule
import com.caferk.movies.di.example.ExampleModule
import com.caferk.movies.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by cafer on 11.4.2018.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AndroidSupportInjectionModule::class),
    (TestUIModule::class), (TestActivitiesModule::class), (ViewModelModule::class),
    (TestMovieDbServiceModule::class), (ExampleModule::class)])
interface TestApplicationComponent {

    @Component.Builder
    interface Builder {
        fun build(): TestApplicationComponent

        @BindsInstance
        fun application(application: BaseTestApplication): Builder
    }

    fun inject(application: BaseTestApplication)

    fun inject(popularMoviesFragmentTest: PopularMoviesFragmentTest)
}