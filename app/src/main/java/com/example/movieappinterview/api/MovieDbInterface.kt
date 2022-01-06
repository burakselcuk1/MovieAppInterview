package com.example.movieappinterview.api

import com.example.movieappinterview.model.Result
import com.example.movieappinterview.model.movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbInterface {



//    https://api.themoviedb.org/3/movie/popular?api_key=6eeb39d6df396b3373f188208038112c

    //get all movies
    @GET("3/movie/popular?api_key=6eeb39d6df396b3373f188208038112c")
    fun getMovieList(): Single<movie>

    //get movie by id
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path ("movie_id") id: Int) : Single<Result>
}
