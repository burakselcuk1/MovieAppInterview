package com.example.movieappinterview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappinterview.R
import com.example.movieappinterview.model.Result


class MovieAdapter( val dataSet: ArrayList<Result>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView
        val movieName: TextView

        init {
            // Define click listener for the ViewHolder's View.
            description = view.findViewById(R.id.movieDescription)
            movieName = view.findViewById(R.id.movieName)
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
        viewHolder.description.text = dataSet[position].overview.toString()
        viewHolder.movieName.text = dataSet[position].original_title.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updataMovieList(movies : List<Result>){
        dataSet.clear()
        dataSet.addAll(movies)
        notifyDataSetChanged()
    }

}
