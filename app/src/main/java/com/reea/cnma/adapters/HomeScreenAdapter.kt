package com.reea.cnma.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reea.cnma.MovieDetailsActivity
import com.reea.cnma.R
import com.reea.cnma.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclelist_item.view.*
import java.util.*

class HomeScreenAdapter(context: Context) : RecyclerView.Adapter<HomeScreenAdapter.MovieHolder>() {
    private var movies : List<Movie> = ArrayList()
    private var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclelist_item, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val currentMovie = movies.get(position)
        holder.movieTitle.text = currentMovie.Title
        Picasso.get().load(currentMovie.Poster).into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            var detailsActivity = Intent(mContext, MovieDetailsActivity::class.java)
            detailsActivity.putExtra("currentMovie", currentMovie)
            mContext.startActivity(detailsActivity)
        }
    }

    override fun getItemCount() = movies.size

    fun setMovies(movies : List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle : TextView = itemView.movieTitle
        val moviePoster: ImageView = itemView.PosterImageView
    }
}