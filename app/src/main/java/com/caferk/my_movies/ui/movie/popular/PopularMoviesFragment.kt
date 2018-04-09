package com.caferk.my_movies.ui.movie.popular

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import com.caferk.kotlinbasearchitecture.domain.entity.ResultsItem
import com.caferk.my_movies.R
import com.caferk.my_movies.ui.adapter.RecyclerMovieAdapter
import com.caferk.my_movies.ui.base.BaseFragment
import javax.inject.Inject

/**
 * Created by cafer on 16.2.2018.
 */
class PopularMoviesFragment : BaseFragment() {

    @Inject
    lateinit var recyclerMovieAdapter: RecyclerMovieAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel

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
        popularMoviesViewModel = ViewModelProviders.of(this, viewModelFactory)[PopularMoviesViewModel::class.java]
        setViewModel(popularMoviesViewModel)
        popularMoviesViewModel.movieListLiveData.observe(this, Observer {
            showPopularMovies(it?.results)
        })
    }

    override fun layoutId(): Int {
        return R.layout.fragment_movies
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBaseActivity().let {
            it.supportActionBar?.title = arguments?.getString(TITLE_KEY)
        }
        popularMoviesViewModel.getMovieList()
    }


    @OnClick(R.id.btn_create_new_note)
    fun onButtonClicked() {
        /*val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID, recyclerMovieAdapter.list[0].id)
        startActivity(intent)*/
    }

    fun showPersonDetails(pair: Pair<String?, String?>) {
        Toast.makeText(context, pair.first + " " + pair.second, Toast.LENGTH_SHORT).show()
    }

    fun showPopularMovies(popularMoviesList: List<ResultsItem>?) {
        recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerMovieAdapter
        recyclerMovieAdapter.fillList(popularMoviesList)
    }
}