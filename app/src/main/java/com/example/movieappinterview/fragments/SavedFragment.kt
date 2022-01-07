package com.example.movieappinterview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieappinterview.R
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.viewmodel.MovieDetailViewModel
import com.example.movieappinterview.viewmodel.SavedMovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*


class SavedFragment : Fragment() {

    private lateinit var savedMovieViewModel: SavedMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get movie id throught args
        val args = this.arguments
        val movieId: String? = args?.getString("movieId","databos")


        savedMovieViewModel = ViewModelProvider(this).get(SavedMovieViewModel::class.java)

        //Get Movie Detail information from api and save to room db
        getMovieDetailFromApiForSave()

    }

    private fun getMovieDetailFromApiForSave() {
        savedMovieViewModel.moviesDetail.observe(viewLifecycleOwner, Observer {
            //Create new object from Result data class for add savedMovieViewModel.addMovie function
            val saveMovie = Result(it.adult, it.backdrop_path, it.id, it.original_language, it.original_title, it.overview, it.popularity, it.poster_path, it.release_date,
            it.original_title, it.video, it.vote_average, it.vote_count)
            savedMovieViewModel.addMovie(saveMovie)
            Toast.makeText(context,"Successfully Added!", Toast.LENGTH_LONG).show()
        })
    }
}