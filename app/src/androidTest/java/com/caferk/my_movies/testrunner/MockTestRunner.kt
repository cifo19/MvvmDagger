package com.caferk.my_movies.testrunner

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.support.test.runner.AndroidJUnitRunner
import com.caferk.my_movies.BaseTestApplication


/**
 * Created by cafer on 11.4.2018.
 */

class MockTestRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, BaseTestApplication::class.java.name, context)
    }
}