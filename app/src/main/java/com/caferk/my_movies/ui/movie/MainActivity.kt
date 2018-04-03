package com.caferk.my_movies.ui.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import com.caferk.my_movies.R
import com.caferk.my_movies.ui.base.BaseActivity
import com.caferk.my_movies.ui.movie.popular.PopularMoviesFragment
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
            addFragment(R.id.fragment_container, PopularMoviesFragment.newInstance("Custom Title"))
        }
    }

    override fun useBackToolbar(): Boolean {
        return false
    }
}