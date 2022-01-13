package com.example.movieappinterview.fragments

import android.app.AlertDialog
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
import kotlinx.android.synthetic.main.single_room_item.*
import kotlinx.android.synthetic.main.single_room_item.view.*
import java.io.Serializable


class SavedFragment : Fragment() {

    private lateinit var savedMovieViewModel: SavedMovieViewModel
    private lateinit var roomAdater: RoomAdapter
    private lateinit var singleMovieData:Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_saved, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // savedMovieViewModel defined
        savedMovieViewModel = ViewModelProvider(this).get(SavedMovieViewModel::class.java)

        // Check if arguments null or not
        if(arguments?.get("movie")!= null) {
            if (requireArguments().getSerializable("movie") != null) {
                singleMovieData = requireArguments().getSerializable("movie") as Result

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
            }
        }
        // Setup saved_movie_recyclerview to Movie Adapter
        saved_movie_recyclerview.layoutManager = LinearLayoutManager(context)

        //Read all room db data and past to recyclerview
        savedMovieViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            roomAdater = RoomAdapter(it as ArrayList<Result>)

            saved_movie_recyclerview.adapter = roomAdater

        })
    }
}