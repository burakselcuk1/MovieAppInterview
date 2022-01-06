package com.example.movieappinterview.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappinterview.R
import com.example.movieappinterview.fragments.DashboardFragment
import com.example.movieappinterview.fragments.DashboardFragmentDirections
import com.example.movieappinterview.model.Result
import com.example.movieappinterview.model.movie
import kotlinx.android.synthetic.main.single_movie_item.view.*


class MovieAdapter( val dataSet: movie) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView
        val movieName: TextView
        val posterImage: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            description = view.findViewById(R.id.movieDescription)
            movieName = view.findViewById(R.id.movieName)
            posterImage = view.findViewById(R.id.movieImage)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_movie_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.description.text = dataSet.results.get(position).overview
        viewHolder.movieName.text = dataSet.results.get(position).original_title

        val url ="https://image.tmdb.org/t/p/w342" +  dataSet.results.get(position).poster_path


        viewHolder.itemView.apply {
            Glide.with(this).load(url).into(viewHolder.itemView.movieImage)
        }

        viewHolder.itemView.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToMovieDetailsFragment()
            Navigation.findNavController(it).navigate(action)
        }





    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.results.size



}
