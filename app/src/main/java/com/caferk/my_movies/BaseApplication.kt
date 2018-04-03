package com.caferk.my_movies

import com.caferk.my_movies.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by cafer on 2.4.2018.
 */
class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
                .baseApplication(this)
                .build()
    }

}