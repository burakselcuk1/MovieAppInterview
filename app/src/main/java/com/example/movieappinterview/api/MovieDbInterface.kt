package com.example.movieappinterview.api

import com.example.movieappinterview.Util.Constans.Companion.API_KEY
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.model.movie
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbInterface {

//    https://api.themoviedb.org/3/movie/299534?api_key=6eeb39d6df396b3373f188208038112c&language=en-US

//    https://api.themoviedb.org/3/movie/popular?api_key=6eeb39d6df396b3373f188208038112c

    //get all movies
    @GET("3/movie/popular?")
    fun getMovieList(
        @Query("api_key")
        api_key: String = API_KEY
    ): Single<movie>

    //get movie by id
    @GET("3/movie/{movie_id}?")
    fun getMovieDetails(
        @Path ("movie_id") id: String,
        @Query("api_key") api_key: String = API_KEY

    ) : Single<Result>

    @GET("3/movie/popular?")
    fun searchForMovie(
        @Query("api_key")
        api_key: String = API_KEY
    ): Response<movie>
}
