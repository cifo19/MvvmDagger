package com.caferk.movies.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.AndroidSupportInjection
import io.reactivex.annotations.Nullable

/**
 * Created by cafer on 16.2.2018.
 */
abstract class BaseFragment : Fragment() {

    private lateinit var unBinder: Unbinder

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    @Nullable
    abstract fun getViewModel(): BaseViewModel?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(layoutId(), container, false)
        try {
            unBinder = ButterKnife.bind(this, view)
        } catch (e: Exception) {
            Log.d("Butterknife exception", e.message)
        }
        /*(activity as BaseActivity).setLoadingState(getViewModel()?.loadingLiveData!!)*/
        return view
    }

    fun getBaseActivity() = activity as BaseActivity

    override fun onDestroy() {
        super.onDestroy()
        unBinder.unbind()
    }
}