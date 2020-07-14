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

class FavoritesScreenAdapter(context: Context) : RecyclerView.Adapter<FavoritesScreenAdapter.FavoritesHolder>() {
    private var favoriteMovies : List<Movie> = ArrayList()
    private var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesScreenAdapter.FavoritesHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclelist_item,parent,false)
        return FavoritesHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favoriteMovies.size
    }

    override fun onBindViewHolder(holder: FavoritesScreenAdapter.FavoritesHolder, position: Int) {
        val currentFavoriteMovie = favoriteMovies.get(position)
        holder.movieTitle.text=currentFavoriteMovie.Title
        Picasso.get().load(currentFavoriteMovie.Poster).into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            var detailsActivity = Intent(mContext, MovieDetailsActivity::class.java)
            detailsActivity.putExtra("currentMovie", currentFavoriteMovie)
            mContext.startActivity(detailsActivity)
        }
    }

    fun setFavorites(favorites : List<Movie>) {
        this.favoriteMovies = favorites
        notifyDataSetChanged()
    }

    class FavoritesHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle : TextView = itemView.movieTitle
        val moviePoster: ImageView = itemView.PosterImageView
    }
}