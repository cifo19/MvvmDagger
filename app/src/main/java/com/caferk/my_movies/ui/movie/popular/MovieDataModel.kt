package com.caferk.my_movies.ui.movie.popular

import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.kotlinbasearchitecture.domain.entity.PersonImages
import com.caferk.kotlinbasearchitecture.domain.entity.PersonMovieCredits
import com.caferk.my_movies.model.RestApi
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by cafer on 2.4.2018.
 */
@Singleton
open class MovieDataModel
@Inject constructor(val restApi: RestApi) {

    fun getPopularMovies(language: String, page: Int): Observable<MovieResults> {
        return restApi.getPopularMovies(language, page)
                .delay(5000, TimeUnit.MILLISECONDS)
                .map { it.body() }
    }

    fun getMovieDetails(movieId: Int): Observable<Pair<String?, String?>> {
        return restApi.getCreditsByMovie(movieId)
                .map { it.body()?.cast?.get(0)?.id }
                .flatMap { it ->
                    Observable
                            .zip(restApi.getPersonMovieCredits(it)
                                    , restApi.getPersonImages(it)
                                    , BiFunction<Response<PersonMovieCredits>, Response<PersonImages>, Pair<PersonMovieCredits?, PersonImages?>>
                            { personMovieCredits, personImages ->
                                Pair(personMovieCredits.body(), personImages.body())
                            })
                }
                .map {
                    Pair(it.first?.cast?.get(0)?.character
                            , it.second?.profiles?.get(0)?.filePath)
                }
    }
}