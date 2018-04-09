package com.caferk.my_movies

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.support.annotation.NonNull
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.my_movies.ui.movie.popular.MovieDataModel
import com.caferk.my_movies.ui.movie.popular.PopularMoviesViewModel
import com.caferk.my_movies.util.gsonUpper
import com.caferk.my_movies.util.parseFileWith
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by cafer on 5.4.2018.
 */

class PopularMoviesViewModelTest {

    @Mock
    lateinit var movieDataModel: MovieDataModel

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    @Mock
    lateinit var movieResultObserver: Observer<MovieResults>

    lateinit var popularMoviesViewModel: PopularMoviesViewModel

    val getPopularMoviesSuccessResponsePath = "get_popular_movies_success.json"
    val language = "en"
    val page = 1


    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                    // this prevents StackOverflowErrors when scheduling with a delay
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        popularMoviesViewModel = PopularMoviesViewModel(movieDataModel)
    }

    @Test
    fun getMovieList_whenGetPopularMoviesSuccess_thenPostMovieList() {

        val popularMoviesResponse: MovieResults = getPopularMoviesSuccessResponsePath parseFileWith gsonUpper
        whenever(movieDataModel.getPopularMovies(language, page)).thenReturn(Observable.just(popularMoviesResponse))

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
        whenever(movieDataModel.getPopularMovies(language, page)).thenReturn(Observable.error(Throwable()))

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