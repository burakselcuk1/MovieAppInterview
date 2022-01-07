package com.example.movieappinterview.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappinterview.api.MovieDbApi
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.model.movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DashboardViewModel: ViewModel() {

    private val apiService = MovieDbApi()
    private val disposable = CompositeDisposable()


    val movies = MutableLiveData<movie>()

    fun getMovies(){
        disposable.addAll(
            apiService.getMovie().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<movie>(){
                    override fun onSuccess(t: movie) {
                        movies.value = t
                        //Log.e("burak", "başarılı" + t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e("burak", "başarısız")
                        e.printStackTrace()
                    }
                })
        )
    }
}

