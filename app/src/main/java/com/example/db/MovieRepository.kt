package com.example.db

import androidx.lifecycle.LiveData
import com.example.movieappinterview.model.Result

class MovieRepository(private val movieDao: Dao) {

    val readAllData: LiveData<List<Result>> = movieDao.readAllData()

    suspend fun addMovie(movie: Result){
        movieDao.addMovie(movie)
    }

}