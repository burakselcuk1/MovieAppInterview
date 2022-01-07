package com.example.movieappinterview.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappinterview.Adapter.MovieAdapter
import com.example.movieappinterview.Adapter.RoomAdapter
import com.example.movieappinterview.R
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.viewmodel.MovieDetailViewModel
import com.example.movieappinterview.viewmodel.SavedMovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_saved.*


class SavedFragment : Fragment() {

    private lateinit var savedMovieViewModel: SavedMovieViewModel
    private lateinit var roomAdater: RoomAdapter
    private lateinit var singleMovieData:Result
    val TAG:String = "TAG_SavedFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        singleMovieData = requireArguments().getSerializable("movie") as Result
        singleMovieData.let {
            Log.e(TAG,"bos degil movie data" + singleMovieData.title)
        }

        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedMovieViewModel = ViewModelProvider(this).get(SavedMovieViewModel::class.java)


        //get movie id throught args
        val args = this.arguments
        val movieId: String? = args?.getString("movieId","databos")

        // Setup saved_movie_recyclerview to Movie Adapter
        saved_movie_recyclerview.layoutManager = LinearLayoutManager(context)

        val saveMovie = Result(singleMovieData.adult,
            singleMovieData.backdrop_path,
            singleMovieData.id,
            singleMovieData.original_language,
            singleMovieData.original_title,
            singleMovieData.overview,
            singleMovieData.popularity,
            singleMovieData.poster_path,
            singleMovieData.release_date,
            singleMovieData.original_title,
            singleMovieData.video,
            singleMovieData.vote_average,
            singleMovieData.vote_count)
        savedMovieViewModel.addMovie(saveMovie)


        //Get Movie Detail information from api and save to room db
      //  getMovieDetailFromApiForSave()

    }

    private fun getMovieDetailFromApiForSave() {
        savedMovieViewModel.moviesDetail.observe(viewLifecycleOwner, Observer {
            //Create new object from Result data class for add savedMovieViewModel.addMovie function

            Toast.makeText(context,"Successfully Added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_movieDetailsFragment_to_savedFragment)
        })
    }
}