package com.example.movieappinterview.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieappinterview.R
import com.example.movieappinterview.Util.Constans.Companion.POSTER_MAIN_URL
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.viewmodel.MovieDetailViewModel
import com.example.movieappinterview.viewmodel.SavedMovieDetailViewModel
import com.example.movieappinterview.viewmodel.SavedMovieViewModel
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

        delete_saved_movie.setOnClickListener {
            deleteMovieFromRoomDb()
        }
    }

    private fun deleteMovieFromRoomDb() {
        savedMovieDetailViewModel.moviesDetail.observe(viewLifecycleOwner, Observer {


            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){_, _ ->
                savedMovieDetailViewModel.deleteMovie(it)
                findNavController().navigate(R.id.action_savedMovieDetailFragment_to_savedFragment)

            }
            builder.setNegativeButton("No"){_, _ ->}
            builder.setTitle("Delete ${it.original_title}")
            builder.setMessage("Are you sure delete this movie ${it.original_title}")
            builder.create().show()
            Toast.makeText(requireContext(),"Movie deleted!", Toast.LENGTH_SHORT).show()

        })
    }

    // Get movies detail from api for saved movie detail screen
    private fun getMoviesDetailFromApi() {

        savedMovieDetailViewModel.moviesDetail.observe(viewLifecycleOwner, Observer{
            //set values into detailFragment components
            it.let {
                resultMovie = it

                saved_movie_detail_title.text = resultMovie.original_title
                saved_movie_detail_tagline.text = resultMovie.overview
                val moviePosterUrl= POSTER_MAIN_URL + resultMovie.poster_path
                val movieBackDropPath = POSTER_MAIN_URL + resultMovie.backdrop_path
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