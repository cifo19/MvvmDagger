package com.caferk.my_movies.ui.main.popular

import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.kotlinbasearchitecture.domain.entity.PersonImages
import com.caferk.kotlinbasearchitecture.domain.entity.PersonMovieCredits
import com.caferk.my_movies.model.RestApi
import com.caferk.my_movies.ui.base.BaseDataModel
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
@Inject constructor(val restApi: RestApi) : BaseDataModel {

    override fun getPopularMovies(language: String, page: Int): Observable<MovieResults> {
        return restApi.getPopularMovies(language, page)
            .delay(1000, TimeUnit.MILLISECONDS)
            .map { it.body() }
    }

    override fun getMovieDetails(movieId: Int): Observable<Pair<String?, String?>> {
        return restApi.getCreditsByMovie(movieId)
            .delay(2000, TimeUnit.MILLISECONDS)
            .map { it.body()?.cast?.get(0)?.id }
            .flatMap { it ->
                Observable
                    .zip(restApi.getPersonMovieCredits(it)
                        ,
                        restApi.getPersonImages(it)
                        ,
                        BiFunction<Response<PersonMovieCredits>, Response<PersonImages>, Pair<PersonMovieCredits?, PersonImages?>>
                        { personMovieCredits, personImages ->
                            Pair(personMovieCredits.body(), personImages.body())
                        })
            }
            .map {
                Pair(
                    it.first?.cast?.get(0)?.character
                    , it.second?.profiles?.get(0)?.filePath
                )
            }
    }
}