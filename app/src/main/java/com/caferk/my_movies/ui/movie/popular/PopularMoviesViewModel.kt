package com.caferk.my_movies.ui.movie.popular

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cafer on 3.4.2018.
 */
class PopularMoviesViewModel @Inject constructor(private val movieDataModel: MovieDataModel) : ViewModel() {

    val movieListData: MutableLiveData<MovieResults> = MutableLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getMovieList() {
        compositeDisposable.add(movieDataModel.getPopularMovies("en", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    movieListData.postValue(it)
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}