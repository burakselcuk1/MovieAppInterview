package com.example.movieappinterview.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieappinterview.db.MovieDatabase
import com.example.movieappinterview.db.MovieRepository
import com.example.movieappinterview.api.MovieDbApi
import com.example.movieappinterview.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


 class SavedMovieDetailViewModel(application: Application): AndroidViewModel(application) {

     private val repository: MovieRepository

    private val apiService = MovieDbApi()
    private val disposable = CompositeDisposable()

    val moviesDetail = MutableLiveData<Result>()

     init {
         val movieDao = MovieDatabase.getDatabase(application).movieDao()
         repository = MovieRepository(movieDao)
     }

    fun getMoviesDetailForSavedDetail(movieId: String){
        disposable.addAll(
            apiService.getMovieDetails(movieId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Result>(){

                    override fun onSuccess(t: Result) {
                        moviesDetail.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }
     fun deleteMovie(movie: Result){
         viewModelScope.launch (Dispatchers.IO){
             repository.deleteMovieFromRoomDb(movie)
         }
     }
    }

