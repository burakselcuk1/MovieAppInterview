package com.example.movieappinterview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappinterview.api.MovieDbApi
import com.example.movieappinterview.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DashboardViewModel: ViewModel() {

    private val apiService = MovieDbApi()
    private val disposable = CompositeDisposable()


    val movies = MutableLiveData<List<Result>>()

    fun getMovies(){
        disposable.addAll(
            apiService.getMovie().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Result>>(){
                    override fun onSuccess(t: List<Result>) {
                        movies.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}

