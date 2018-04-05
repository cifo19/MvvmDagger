package com.caferk.my_movies.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by cafer on 5.4.2018.
 */
open class BaseViewModel : ViewModel() {
    val loading : MutableLiveData<Boolean> = MutableLiveData()
}