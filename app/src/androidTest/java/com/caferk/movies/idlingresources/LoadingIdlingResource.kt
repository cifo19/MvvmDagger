package com.caferk.movies.idlingresources

import android.os.Handler
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.IdlingResource.ResourceCallback
import com.caferk.movies.ui.base.BaseActivity

/**
 * Created by cafer on 29.4.2018.
 */
class LoadingIdlingResource(private val baseActivity: BaseActivity) : IdlingResource {

    companion object {
        private const val IDLE_POLL_DELAY_MILLIS: Long = 100
    }

    lateinit var resourceCallback: ResourceCallback

    override fun getName(): String = "Is loading Idle"

    override fun isIdleNow(): Boolean {
        val canGoIdle = baseActivity.run {
            isLoadingShowed.and(isLoaderShowing().not())
        }

        if (canGoIdle)
            resourceCallback.onTransitionToIdle()
        else {
            //https://gist.github.com/vaughandroid/e2fda716c7cf6853fa79
            Handler().postDelayed({ isIdleNow }, IDLE_POLL_DELAY_MILLIS)
        }

        return canGoIdle
    }

    override fun registerIdleTransitionCallback(resourceCallback: ResourceCallback) {
        this.resourceCallback = resourceCallback
    }
}