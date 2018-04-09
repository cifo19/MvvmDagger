package com.caferk.my_movies.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelStore
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.caferk.my_movies.R
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by cafer on 2.4.2018.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    lateinit var toolbar: Toolbar
    lateinit var progressBar: ProgressBar
    lateinit var relativeLayout: RelativeLayout
    private var viewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        relativeLayout = findViewById(R.id.root) as RelativeLayout
        initializeActivityComponent()
        initializeActivity(savedInstanceState)
        initializeProgressBar()
        initializeToolbar()
        if (viewModel != null){
            setLoadingState(viewModel?.loadingLiveData!!)
        }
    }

    internal abstract fun initializeActivity(savedInstanceState: Bundle?)

    internal fun initializeToolbar() {
        if (useToolbar()) {
            setSupportActionBar(toolbar)
            if (useBackToolbar()) {
                supportActionBar?.run {
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
                toolbar.setNavigationOnClickListener { finish() }
            }
        } else {
            toolbar.visibility = View.GONE
        }
    }

    protected fun setViewModel(viewModel: BaseViewModel?) {
        this.viewModel = viewModel
    }

    private fun initializeActivityComponent() {
        AndroidInjection.inject(this)
    }

    private fun useToolbar(): Boolean = true
    open fun useBackToolbar(): Boolean = true

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    private fun initializeProgressBar() {
        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
        val params = RelativeLayout.LayoutParams(100, 100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        relativeLayout.addView(progressBar, params)
        progressBar.visibility = View.GONE
    }

    fun isLoaderShowing(): Boolean? = progressBar.visibility == View.VISIBLE

    fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    fun context(): Context {
        return applicationContext
    }

    fun setLoadingState(loadingLiveData: MutableLiveData<Boolean>) = loadingLiveData.observe(
        this, Observer {
            when (it) {
                true -> showLoader()
                false -> hideLoader()
            }
        }
    )
}