package com.example.movieappinterview.fragments.MovieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieappinterview.Adapter.MovieAdapter
import com.example.movieappinterview.R

import com.example.movieappinterview.api.MovieDbApi
import com.example.movieappinterview.api.MovieDbInterface
import com.example.movieappinterview.model.movie
import com.example.movieappinterview.viewmodel.DashboardViewModel

import com.example.movieappinterview.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import java.text.NumberFormat
import java.util.*


class MovieDetailsFragment : Fragment() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val args = this.arguments
        val movieId: String? = args?.getString("movieId","databos")

        //val movieId="299534"

        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        movieDetailViewModel.getMoviesDetail(movieId!!)

        getMoviesDetailFromApi()


    }

    //Get movie detail information from api and paste fragment_movie_detail.xml page
    private fun getMoviesDetailFromApi() {

        movieDetailViewModel.moviesDetail.observe(viewLifecycleOwner, Observer{
            movie_detail_title.text = it.original_title
            movie_detail_tagline.text = it.overview
            val moviePosterUrl= "https://image.tmdb.org/t/p/w342/" + it.poster_path
            Glide.with(this)
                .load(moviePosterUrl)
                .into(movie_detail_poster)
        })
    }
}