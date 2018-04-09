package com.caferk.my_movies.ui.base

import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import io.reactivex.Observable

/**
 * Created by cafer on 6.4.2018.
 */
interface BaseDataModel {

    fun getPopularMovies(language: String, page: Int): Observable<MovieResults>

    fun getMovieDetails(movieId: Int): Observable<Pair<String?, String?>>
}