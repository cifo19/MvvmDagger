package com.caferk.my_movies.ui.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.AndroidSupportInjection

/**
 * Created by cafer on 16.2.2018.
 */
abstract class BaseFragment : Fragment() {

    private lateinit var unBinder: Unbinder
    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    protected fun setViewModel(viewModel: BaseViewModel){
        this.viewModel = viewModel
    }

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
        setLoadingState()
        return view
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    private fun setLoadingState() = viewModel.loadingLiveData.observe(
            this, Observer {
        when (it) {
            true -> (activity as BaseActivity).showLoader()
            false -> (activity as BaseActivity).hideLoader()
        }
    }
    )

    override fun onDestroy() {
        super.onDestroy()
        unBinder.unbind()
    }
}