package com.caferk.movies.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.caferk.movies.R
import com.caferk.movies.ui.base.BaseActivity
import com.caferk.movies.ui.base.BaseViewModel
import com.caferk.movies.ui.main.popular.PopularMoviesFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, PopularMoviesFragment.newInstance("Custom Title"), PopularMoviesFragment::class.java.name)
        }
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    override fun useBackToolbar(): Boolean {
        return false
    }
}