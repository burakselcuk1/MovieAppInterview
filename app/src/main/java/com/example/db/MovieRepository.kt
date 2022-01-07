package com.example.db

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.movieappinterview.model.Result

class MovieRepository(private val movieDao: Dao) {

    val TAG: String = "TAG_MovieRepository"


    val readAllData: LiveData<List<Result>> = movieDao.readAllData()

    suspend fun addMovie(movie: Result){
        Log.e(TAG,"girdi-movie2")
        movieDao.addMovie(movie)
    }

}