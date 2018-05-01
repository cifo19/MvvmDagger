package com.caferk.movies.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by cafer on 5.4.2018.
 */
abstract class BaseViewModel : ViewModel() {

    val loadingLiveData : MutableLiveData<Boolean> = MutableLiveData()
}