package com.caferk.movies.model

import com.caferk.kotlinbasearchitecture.domain.entity.Credits
import com.caferk.kotlinbasearchitecture.domain.entity.MovieResults
import com.caferk.kotlinbasearchitecture.domain.entity.PersonImages
import com.caferk.kotlinbasearchitecture.domain.entity.PersonMovieCredits
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by cafer on 2.4.2018.
 */
interface RestApi {
    val BASE_URL: String
        get() = "https://api.themoviedb.org/"

    @GET("movie/popular")
    fun getPopularMovies(@Query("language") language: String,
                         @Query("page") page: Int): Observable<Response<MovieResults>>

    @GET("/movie/{movie_id}/credits")
    fun getCreditsByMovie(@Path("movie_id") movieId: Int): Observable<Response<Credits>>

    @GET("/person/{person_id}/movie_credits")
    fun getPersonMovieCredits(@Path("person_id") personId: Int?): Observable<Response<PersonMovieCredits>>

    @GET("/person/{person_id}/images")
    fun getPersonImages(@Path("person_id") personId: Int?): Observable<Response<PersonImages>>
}