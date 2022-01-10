package com.example.movieappinterview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.movieappinterview.R

import com.example.movieappinterview.model.Result

import com.example.movieappinterview.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*


class MovieDetailsFragment : Fragment() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    lateinit var resultMovie:Result




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get movie id throught args
        val args = this.arguments
        val movieId: String? = args?.getString("movieId","databos")

        //MovieViewModel definitions
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        movieDetailViewModel.getMoviesDetail(movieId!!)
        getMoviesDetailFromApi()

        //Goes to SavedFragments
        save_movie_button.setOnClickListener {

            var bundle = Bundle()

            resultMovie.let {
                bundle.putSerializable("movie", resultMovie)

                val navigationController = Navigation.findNavController(view)
                navigationController.navigate(R.id.action_movieDetailsFragment_to_savedFragment, bundle)

            }


        }
    }

    //Get movie detail information from api and paste fragment_movie_detail.xml page
    private fun getMoviesDetailFromApi() {

        movieDetailViewModel.moviesDetail.observe(viewLifecycleOwner, Observer{
            //set values into detailFragment components

            it.let {
                resultMovie = it

                movie_detail_title.text = resultMovie.original_title
                movie_detail_tagline.text = resultMovie.overview
                val moviePosterUrl= "https://image.tmdb.org/t/p/w342/" + resultMovie.poster_path
                val movieBackDropPath ="https://image.tmdb.org/t/p/w342/" + resultMovie.backdrop_path
                Glide.with(this)
                    .load(moviePosterUrl)
                    .into(movie_detail_poster)

                Glide.with(this)
                    .load(movieBackDropPath)
                    .into(main_poster)
            }

        })
    }
}