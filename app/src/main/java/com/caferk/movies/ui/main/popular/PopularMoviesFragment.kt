package com.caferk.movies.ui.main.popular

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import com.caferk.kotlinbasearchitecture.domain.entity.ResultsItem
import com.caferk.movies.R
import com.caferk.movies.ui.adapter.RecyclerMovieAdapter
import com.caferk.movies.ui.base.BaseFragment
import com.caferk.movies.ui.detail.DetailActivity
import javax.inject.Inject

/**
 * Created by cafer on 16.2.2018.
 */
class PopularMoviesFragment : BaseFragment() {

    @Inject
    lateinit var recyclerMovieAdapter: RecyclerMovieAdapter

    @Inject
    lateinit var popularMoviesViewModel: PopularMoviesViewModel

    @BindView(R.id.frPopularMovies_rvMovies)
    lateinit var recyclerView: RecyclerView

    companion object {
        const val TITLE_KEY: String = "frPopularMoviesTitle"
        fun newInstance(title: String): PopularMoviesFragment {
            val args = Bundle()
            args.putString(TITLE_KEY, title)
            val popularMoviesFragment = PopularMoviesFragment()
            popularMoviesFragment.arguments = args
            return popularMoviesFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMoviesViewModel.movieListLiveData.observe(this, Observer {
            showPopularMovies(it?.results)
        })
    }

    override fun layoutId() = R.layout.fragment_movies

    override fun getViewModel() = popularMoviesViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBaseActivity().let {
            it.supportActionBar?.title = arguments?.getString(TITLE_KEY)
        }
        popularMoviesViewModel.getMovieList()
    }

    @OnClick(R.id.btn_create_new_note)
    fun onButtonClicked() {
        if (!recyclerMovieAdapter.list.isEmpty()) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE_ID, recyclerMovieAdapter.list[0].id)
            startActivity(intent)
        } else {
            Toast.makeText(context, getString(R.string.empty_list_error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPopularMovies(popularMoviesList: List<ResultsItem>?) {
        recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerMovieAdapter
        recyclerMovieAdapter.fillList(popularMoviesList)
    }
}