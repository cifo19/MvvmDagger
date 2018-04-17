package com.caferk.my_movies.di

import com.caferk.my_movies.BaseTestApplication
import com.caferk.my_movies.PopularMoviesFragmentTest
import com.caferk.my_movies.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by cafer on 11.4.2018.
 */
@Singleton
@Component(
    modules = [(AndroidInjectionModule::class), (AndroidSupportInjectionModule::class),
        (TestUIModule::class), (TestActivitiesModule::class), (ViewModelModule::class),
        (TestMovieDbServiceModule::class), (TestDataModule::class), (TestDemoModule::class)]
)
interface TestApplicationComponent {

    @Component.Builder
    interface Builder {
        fun build(): TestApplicationComponent

        @BindsInstance
        fun application(application: BaseTestApplication): Builder

        @BindsInstance
        fun services(services: Services): Builder
    }

    fun inject(application: BaseTestApplication)

    fun inject(popularMoviesFragmentTest: PopularMoviesFragmentTest)
}