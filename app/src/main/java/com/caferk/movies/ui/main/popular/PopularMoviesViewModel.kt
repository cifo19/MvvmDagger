package com.caferk.movies.ui.main.popular

import android.arch.lifecycle.MutableLiveData
import com.caferk.movies.model.entity.MovieResults
import com.caferk.movies.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cafer on 3.4.2018.
 */
class PopularMoviesViewModel @Inject
constructor(private val movieDataModel: MovieDataModel) : BaseViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val movieListLiveData: MutableLiveData<MovieResults> = MutableLiveData()

    fun getMovieList() {
        compositeDisposable.add(movieDataModel.getPopularMovies("en", 1)
                .doOnSubscribe { loadingLiveData.postValue(true) }
                .doOnError {
                    movieListLiveData.postValue(MovieResults(results = emptyList()))
                }
                .doOnTerminate {
                    loadingLiveData.postValue(false)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            movieListLiveData.postValue(it)
                        },
                        {
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

data class DataHolder(val name: String, val surName: String, val age: Int)

class DataRetriever {

    fun takeDoAnyOnDataHolder(dataAnalyzer: DataAnalyzer) {
        val dataHolder = DataHolder("Cafer", "Kaya", 15)
        dataAnalyzer.printDataInformations(dataHolder)
    }
}

class DataAnalyzer {

    fun printDataInformations(dataHolder: DataHolder): String {
        return dataHolder.toString()
    }
}