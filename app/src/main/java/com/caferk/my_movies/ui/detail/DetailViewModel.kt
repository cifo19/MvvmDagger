package com.caferk.my_movies.ui.detail

import android.arch.lifecycle.MutableLiveData
import com.caferk.my_movies.ui.base.BaseViewModel
import com.caferk.my_movies.ui.main.popular.MovieDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(val movieDataModel: MovieDataModel) : BaseViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val pairLiveData: MutableLiveData<Pair<String?, String?>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Unit> = MutableLiveData()

    fun getMovieDetail(movieId: Int) {

        if (movieId == -1) {
            errorLiveData.postValue(null)
            return
        }

        compositeDisposable.add(movieDataModel.getMovieDetails(movieId = movieId)
            .doOnSubscribe { loadingLiveData.postValue(true) }
            .doOnTerminate { loadingLiveData.postValue(false) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                pairLiveData.postValue(it)
            },{
                errorLiveData.postValue(null)
            }))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}