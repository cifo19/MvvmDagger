package com.caferk.my_movies

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.caferk.my_movies.di.DaggerTestApplicationComponent
import com.caferk.my_movies.di.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class BaseTestApplication : Application() , HasActivityInjector, HasSupportFragmentInjector {

    @Inject lateinit var hasActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var hasFragmentInjector: DispatchingAndroidInjector<Fragment>
    lateinit var testApplicationComponent: TestApplicationComponent

    override fun onCreate() {
        super.onCreate()

        testApplicationComponent = DaggerTestApplicationComponent.create()
        testApplicationComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return hasActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return hasFragmentInjector
    }
}
