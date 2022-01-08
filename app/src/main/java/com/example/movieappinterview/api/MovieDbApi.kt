package com.example.movieappinterview.api

import com.example.movieappinterview.Util.Constans.Companion.BASE_URL
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.model.movie
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieDbApi {

    // DETAIL LINK https://api.themoviedb.org/3/movie/299534?api_key=6eeb39d6df396b3373f188208038112c&language=en-US



    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieDbInterface::class.java)

    fun getMovie(): Single<movie> {
        return api.getMovieList()
    }

    fun getMovieDetails(movieId:String) : Single<Result>{
        return api.getMovieDetails(movieId)
    }
}


