package com.caferk.movies

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.movies.ui.main.popular.MovieDataModel
import com.caferk.movies.ui.main.popular.PopularMoviesViewModel
import com.caferk.movies.util.gsonUpper
import com.caferk.movies.util.parseFileWith
import com.caferk.movies.utils.TestUtils
import com.caferk.movies.util.parseFile
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by cafer on 5.4.2018.
 */

class PopularMoviesViewModelTest {

    @Mock
    private lateinit var movieDataModel: MovieDataModel

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var movieResultObserver: Observer<MovieResults>

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel

    private val getPopularMoviesSuccessResponsePath = "get_popular_movies_success.json"
    private val language = "en"
    private val page = 1

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        TestUtils.initRxJavaSchedulers()
        popularMoviesViewModel = PopularMoviesViewModel(movieDataModel)
    }

    @Test
    fun getMovieList_whenGetPopularMoviesSuccess_thenPostMovieList() {

        val popularMoviesResponse: MovieResults =
            getPopularMoviesSuccessResponsePath.parseFile()
        whenever(movieDataModel.getPopularMovies(language, page)).thenReturn(
            Observable.just(
                popularMoviesResponse
            )
        )

        popularMoviesViewModel.loadingLiveData.observeForever(loadingObserver)
        popularMoviesViewModel.movieListLiveData.observeForever(movieResultObserver)

        popularMoviesViewModel.getMovieList()

        verify(movieDataModel).getPopularMovies(language, page)
        verify(loadingObserver).onChanged(true)
        verify(movieResultObserver).onChanged(popularMoviesResponse)
        assertEquals(popularMoviesResponse, popularMoviesViewModel.movieListLiveData.value)
        verify(loadingObserver).onChanged(false)
    }

    @Test
    fun getMovieList_whenGetPopularMoviesError_ThenReturnEmptyList() {

        val movieResults = MovieResults(results = emptyList())
        whenever(movieDataModel.getPopularMovies(language, page)).thenReturn(
            Observable.error(
                Throwable()
            )
        )

        popularMoviesViewModel.loadingLiveData.observeForever(loadingObserver)
        popularMoviesViewModel.movieListLiveData.observeForever(movieResultObserver)

        popularMoviesViewModel.getMovieList()

        verify(movieDataModel).getPopularMovies(language, page)
        verify(loadingObserver).onChanged(true)
        verify(movieResultObserver).onChanged(movieResults)
        assertEquals(movieResults, popularMoviesViewModel.movieListLiveData.value)
        verify(loadingObserver).onChanged(false)
    }
}