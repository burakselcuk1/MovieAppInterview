package com.example.movieappinterview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieappinterview.R
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.viewmodel.MovieDetailViewModel
import com.example.movieappinterview.viewmodel.SavedMovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_saved_movie_detail.*



class SavedMovieDetailFragment : Fragment() {

    private lateinit var savedMovieDetailViewModel: SavedMovieDetailViewModel
    lateinit var resultMovie:Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get movie id throught args
        val args = this.arguments
        val movieId: String? = args?.getString("movieIdd","databos")

        //MovieViewModel definitions
        savedMovieDetailViewModel = ViewModelProvider(this).get(SavedMovieDetailViewModel::class.java)
        savedMovieDetailViewModel.getMoviesDetailForSavedDetail(movieId!!)
        getMoviesDetailFromApi()



    }


    // Get movies detail from api for saved movie detail screen
    private fun getMoviesDetailFromApi() {

        savedMovieDetailViewModel.moviesDetail.observe(viewLifecycleOwner, Observer{
            //set values into detailFragment components

            it.let {
                resultMovie = it

                saved_movie_detail_title.text = resultMovie.original_title
                saved_movie_detail_tagline.text = resultMovie.overview
                val moviePosterUrl= "https://image.tmdb.org/t/p/w342/" + resultMovie.poster_path
                val movieBackDropPath ="https://image.tmdb.org/t/p/w342/" + resultMovie.backdrop_path
                Glide.with(this)
                    .load(moviePosterUrl)
                    .into(saved_movie_detail_poster)

                Glide.with(this)
                    .load(movieBackDropPath)
                    .into(saved_main_poster)
            }

        })


    }

}