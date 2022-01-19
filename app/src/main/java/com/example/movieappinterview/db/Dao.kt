package com.example.movieappinterview.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.movieappinterview.model.Result

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie : Result)

    @Delete
    suspend fun deleteMovie(movie: Result)

    @Query("SELECT * FROM movie_data ORDER BY id ASC")
     fun readAllData(): LiveData<List<Result>>
}