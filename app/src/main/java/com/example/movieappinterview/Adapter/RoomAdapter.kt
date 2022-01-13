package com.example.movieappinterview.Adapter

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappinterview.R
import com.example.movieappinterview.Util.Constans.Companion.POSTER_MAIN_URL
import com.example.movieappinterview.fragments.SavedFragmentDirections
import com.example.movieappinterview.fragments.SavedMovieDetailFragment
import com.example.movieappinterview.model.Result
import kotlinx.android.synthetic.main.single_movie_item.view.*
import kotlinx.android.synthetic.main.single_room_item.view.*


class RoomAdapter(private val dataSet: ArrayList<Result>) :
    RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName: TextView
        val overview: TextView
        val roomMovieImage: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            movieName = view.findViewById(R.id.room_movieName)
            overview = view.findViewById(R.id.room_movieDescription)
            roomMovieImage = view.findViewById(R.id.room_movieImage)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_room_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.movieName.text = dataSet[position].original_title
        viewHolder.overview.text = dataSet[position].overview

        val url =POSTER_MAIN_URL +  dataSet.get(position).poster_path

        viewHolder.itemView.apply {
            Glide.with(this).load(url).into(viewHolder.itemView.room_movieImage)
        }

        val result: Result = dataSet.get(position)
        viewHolder.overview.text = result.overview
        viewHolder.movieName.text = result.original_title

        // Goes to saved_movie_detail_fragment
        viewHolder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("movieIdd", ""+result.id)


            val navigationController = Navigation.findNavController(viewHolder.itemView)
            navigationController.navigate(R.id.savedMovieDetailFragment,bundle!!)

            /*
            val action = SavedFragmentDirections.actionSavedFragmentToSavedMovieDetailFragment()
            Navigation.findNavController(it).navigate(action)
*/
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(note: ArrayList<Result>){
        dataSet.clear()
        dataSet.addAll(note)
        notifyDataSetChanged()
    }

}
