package com.caferk.my_movies.ui.main.popular

import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.kotlinbasearchitecture.domain.entity.PersonImages
import com.caferk.kotlinbasearchitecture.domain.entity.PersonMovieCredits
import com.caferk.my_movies.model.RestApi
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by cafer on 2.4.2018.
 */
class MovieDataModel
@Inject constructor(val restApi: RestApi) {

    private var movieDetails: Observable<Pair<PersonMovieCredits?, PersonImages?>>? = null

    fun getPopularMovies(language: String, page: Int): Observable<MovieResults> {
        return restApi.getPopularMovies(language, page)
                .delay(1000, TimeUnit.MILLISECONDS)
                .map { it.body() }
    }

    @Synchronized
    fun getMovieDetails(movieId: Int): Observable<Pair<PersonMovieCredits?, PersonImages?>>? {
        if (movieDetails == null) {
            movieDetails = restApi.getCreditsByMovie(movieId)
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
        }
        return movieDetails
    }
}
