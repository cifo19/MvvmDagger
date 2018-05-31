package com.caferk.movies.ui.main

import android.os.Bundle
import com.caferk.movies.ui.base.BaseActivity
import com.caferk.movies.ui.main.popular.PopularMoviesFragment

class MainActivity : BaseActivity() {

    override fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(
                PopularMoviesFragment.newInstance("Custom Title"),
                PopularMoviesFragment::class.java.name
            )
        }
    }

    override fun useBackToolbar() = false
}