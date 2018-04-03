package com.caferk.my_movies.model

import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import io.reactivex.Observable

/**
 * Created by cafer on 2.4.2018.
 */
interface MovieRepository {
    fun getPopularMovies(language: String, page: Int): Observable<MovieResults>
    fun getMovieDetails(movieId: Int): Observable<Pair<String?, String?>>
}