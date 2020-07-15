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
import kotlinx.android.synthetic.main.recycleview_search_list.view.*

class SearchScreenAdapter(context: Context) : RecyclerView.Adapter<SearchScreenAdapter.SearchHolder>() {
    private var searchedMovies :  List<Movie> = ArrayList()
    private var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_search_list, parent, false)
        return SearchHolder(itemView)
    }

    override fun getItemCount(): Int {
        return searchedMovies.size
    }

    override fun onBindViewHolder(holder: SearchScreenAdapter.SearchHolder, position: Int) {
        val currentSearchMovie = searchedMovies.get(position)
        holder.movieTitle.text=currentSearchMovie.Title
        Picasso.get().load(currentSearchMovie.Poster).into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            var detailsActivity = Intent(mContext, MovieDetailsActivity::class.java)
            detailsActivity.putExtra("currentMovie", currentSearchMovie)
            mContext.startActivity(detailsActivity)
        }
    }

    fun setSearched(searched : List<Movie>) {
        this.searchedMovies = searched
        notifyDataSetChanged()
    }

    class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle : TextView = itemView.movieTitleTextView
        val moviePoster: ImageView = itemView.moviePoster
    }
}