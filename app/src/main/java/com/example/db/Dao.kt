package com.example.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.movieappinterview.model.Result

@Dao
interface Dao {

    //Add new movie into to room db
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie : Result)
}