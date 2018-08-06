package com.caferk.movies

import android.app.Activity
import android.app.Application
import com.caferk.movies.di.component.ApplicationComponent
import com.caferk.movies.di.component.DaggerApplicationComponent
import com.crashlytics.android.Crashlytics
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import javax.inject.Inject

/**
 * Created by cafer on 2.4.2018.
 */
open class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var applicationComponent: ApplicationComponent

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().baseApplication(this).build()
        applicationComponent.inject(this)
        Fabric.with(this, Crashlytics())
    }
}