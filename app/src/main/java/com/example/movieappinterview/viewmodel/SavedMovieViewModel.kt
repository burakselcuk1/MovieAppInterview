package com.example.movieappinterview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieappinterview.db.MovieDatabase
import com.example.movieappinterview.db.MovieRepository
import com.example.movieappinterview.api.MovieDbApi
import com.example.movieappinterview.model.Result
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedMovieViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Result>>
    private val repository: MovieRepository

    private val apiService = MovieDbApi()
    private val disposable = CompositeDisposable()

    val moviesSaved = MutableLiveData<Result>()
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