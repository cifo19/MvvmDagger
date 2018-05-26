package com.caferk.movies.ui.detail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.caferk.movies.ui.base.BaseActivity
import com.caferk.movies.ui.base.BaseViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var detailViewModel: DetailViewModel

    companion object {
        const val MOVIE_ID: String = "movie_id"
    }

    override fun initializeActivity(savedInstanceState: Bundle?) {
        val movieId = intent.getIntExtra(MOVIE_ID, -1)

        detailViewModel.let {
            it.errorLiveData.observe(this, Observer {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            })

            it.pairLiveData.observe(this, Observer {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            })
        }

        detailViewModel.getMovieDetail(movieId = movieId)
    }

    override fun getViewModel(): BaseViewModel? {
        return detailViewModel
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}