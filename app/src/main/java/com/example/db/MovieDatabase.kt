package com.example.db

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null


        private val lock = Any()


       fun getDatabase(context: Context): MovieDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_data"
                ).build()
                INSTANCE =instance
                return instance
            }
        }

    }
}