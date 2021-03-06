package com.caferk.movies.di.component

import com.caferk.movies.BaseApplication
import com.caferk.movies.di.module.ActivitiesModule
import com.caferk.movies.di.module.ApplicationModule
import com.caferk.movies.di.module.MovieDbServiceModule
import com.caferk.movies.di.module.UIModule
import com.caferk.movies.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by cafer on 2.4.2018.
 */
@Singleton
@Component(
        modules = [(ApplicationModule::class), (MovieDbServiceModule::class),
            (ActivitiesModule::class), (UIModule::class), (ViewModelModule::class),
            (AndroidInjectionModule::class), (AndroidSupportInjectionModule::class)]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun baseApplication(application: BaseApplication): Builder
    }

    fun inject(baseApplication: BaseApplication)
}