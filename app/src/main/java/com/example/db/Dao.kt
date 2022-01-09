package com.example.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappinterview.model.Result

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie : Result)

    @Query("SELECT * FROM movie_data ORDER BY id ASC")
     fun readAllData(): LiveData<List<Result>>
}