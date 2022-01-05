package com.example.movieappinterview.api

import com.example.movieappinterview.model.Result
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieDbApi {

    private val BASE_URL ="https://api.themoviedb.org"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieDbInterface::class.java)

    fun getMovie(): Single<List<Result>> {
        return api.getMovieList()
    }
}


