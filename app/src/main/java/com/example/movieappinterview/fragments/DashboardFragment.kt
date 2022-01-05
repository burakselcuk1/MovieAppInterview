package com.example.movieappinterview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappinterview.Adapter.MovieAdapter
import com.example.movieappinterview.R
import com.example.movieappinterview.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var adapter = MovieAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        dashboardViewModel.getMovies()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        getMoviesFromApi()
    }

    private fun getMoviesFromApi() {
        dashboardViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->

            movies?.let {
                recyclerView.visibility = View.VISIBLE
                adapter.updataMovieList(it)
            }

        })
    }

}