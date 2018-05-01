package com.caferk.movies.di

import com.caferk.kotlinbasearchitecture.domain.entity.ResultsItem
import dagger.Module
import dagger.Provides

/**
 * Created by cafer on 11.4.2018.
 */
@Module
class TestUIModule {
    @Provides
    fun provideResultItemList(): MutableList<ResultsItem> {
        return emptyList<ResultsItem>().toMutableList()
    }
}