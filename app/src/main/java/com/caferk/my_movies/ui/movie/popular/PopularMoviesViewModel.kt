package com.caferk.my_movies.ui.movie.popular

import android.arch.lifecycle.MutableLiveData
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.my_movies.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by cafer on 3.4.2018.
 */
class PopularMoviesViewModel @Inject
constructor(private val movieDataModel: MovieDataModel)
    : BaseViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val movieListLiveData: MutableLiveData<MovieResults> = MutableLiveData()

    fun getMovieList() {
        compositeDisposable.add(movieDataModel.getPopularMovies("en", 1)
                .doOnSubscribe { loadingLiveData.postValue(true) }
                .doOnError {
                    movieListLiveData.postValue(MovieResults(results = emptyList()))
                    loadingLiveData.postValue(false)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    movieListLiveData.postValue(it)
                    loadingLiveData.postValue(false)
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}