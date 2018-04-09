package com.caferk.my_movies.di.module

import android.arch.lifecycle.MutableLiveData
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.kotlinbasearchitecture.domain.entity.ResultsItem
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by cafer on 13.3.2018.
 */
@Module
class UIModule {
    @Provides
    fun provideResultItemList(): MutableList<ResultsItem> {
        return emptyList<ResultsItem>().toMutableList()
    }
}