package com.example.db

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappinterview.model.Result

@Database(entities = [Result::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null


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
                ).fallbackToDestructiveMigration().build()
                INSTANCE =instance
                return instance
            }
        }

    }
}