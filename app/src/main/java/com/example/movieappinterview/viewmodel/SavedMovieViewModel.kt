package com.example.movieappinterview.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.db.MovieDatabase
import com.example.db.MovieRepository
import com.example.movieappinterview.api.MovieDbApi
import com.example.movieappinterview.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedMovieViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Result>>
    private val repository: MovieRepository

    private val apiService = MovieDbApi()
    private val disposable = CompositeDisposable()

    val moviesDetail = MutableLiveData<Result>()
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

    fun getMoviesDetail(movieId: String){
        disposable.addAll(
            apiService.getMovieDetails(movieId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Result>(){

                    override fun onSuccess(t: Result) {
                        moviesDetail.value = t
                        Log.e("burak", "başarılı" + t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e("burak", "başarısız "+e.toString())
                        e.printStackTrace()
                    }

                })
        )
    }

}