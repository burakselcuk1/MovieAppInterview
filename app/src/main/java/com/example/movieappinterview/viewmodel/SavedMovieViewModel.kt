package com.example.movieappinterview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.db.MovieDatabase
import com.example.db.MovieRepository
import com.example.movieappinterview.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedMovieViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Result>>
    private val repository: MovieRepository
    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
        readAllData = repository.readAllData
    }

    fun addMovie(movie: Result){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovie(movie)
        }
    }

}