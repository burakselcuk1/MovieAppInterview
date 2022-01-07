package com.example.movieappinterview.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.db.MovieDatabase
import com.example.db.MovieRepository
import com.example.movieappinterview.model.Result

class SavedMovieViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Result>>
    private val repository: MovieRepository
    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository =MovieRepository(movieDao)
        readAllData = repository.readAllData

    }

}