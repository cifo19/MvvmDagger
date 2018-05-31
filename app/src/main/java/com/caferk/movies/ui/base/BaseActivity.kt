package com.caferk.movies.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.caferk.movies.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.annotations.Nullable
import javax.inject.Inject

/**
 * Created by cafer on 2.4.2018.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var relativeLayout: RelativeLayout
    var isLoadingShowed: Boolean = false

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        toolbar = findViewById(R.id.toolbar)
        relativeLayout = findViewById(R.id.root)
        injection()
        initializeActivity(savedInstanceState)
        initializeProgressBar()
        initializeToolbar()

        if (getViewModel() != null) {
            setLoadingState(getViewModel()?.loadingLiveData!!)
        }
    }

    internal abstract fun initializeActivity(savedInstanceState: Bundle?)

    private fun initializeToolbar() {
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

    @Nullable
    open fun getViewModel(): BaseViewModel? = null

    private fun injection() = AndroidInjection.inject(this)

    private fun useToolbar(): Boolean = true
    open fun useBackToolbar(): Boolean = true

    protected fun addFragment(fragment: Fragment, fragmentTag: String = "") {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTag)
        fragmentTransaction.commit()
    }

    private fun initializeProgressBar() {
        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
        val params = RelativeLayout.LayoutParams(100, 100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        relativeLayout.addView(progressBar, params)
        progressBar.visibility = View.GONE
    }

    fun isLoaderShowing(): Boolean = progressBar.visibility == View.VISIBLE

    private fun showLoader() {
        progressBar.visibility = View.VISIBLE
        isLoadingShowed = true
    }

    private fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    fun context(): Context {
        return applicationContext
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    fun setLoadingState(loadingLiveData: MutableLiveData<Boolean>) = loadingLiveData.observe(
        this, Observer {
            when (it) {
                true -> if (!isLoaderShowing()) showLoader()
                false -> if (isLoaderShowing()) hideLoader()
            }
        }
    )
}