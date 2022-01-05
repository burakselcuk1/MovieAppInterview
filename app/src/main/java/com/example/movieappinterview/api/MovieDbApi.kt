package com.example.movieappinterview.api

import com.example.movieappinterview.model.movie
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDbApi {

    private val BASE_URL ="https://www.themoviedb.org/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieDbInterface::class.java)

    fun getMovie(cName: String): Single<List<movie>> {
        return api.getMovieList(cName)
    }

}