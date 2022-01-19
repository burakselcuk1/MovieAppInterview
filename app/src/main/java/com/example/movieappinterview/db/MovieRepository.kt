package com.example.movieappinterview.db

import androidx.lifecycle.LiveData
import com.example.movieappinterview.db.Dao
import com.example.movieappinterview.model.Result

class MovieRepository(private val movieDao: Dao) {

    val readAllData: LiveData<List<Result>> = movieDao.readAllData()

     // Add a new movie to RoomDB
    suspend fun addMovie(movie: Result){
        movieDao.addMovie(movie)
    }
    //Delete a single movie from RoomDb
    suspend fun deleteMovieFromRoomDb(movie: Result){
        movieDao.deleteMovie(movie)
    }
}