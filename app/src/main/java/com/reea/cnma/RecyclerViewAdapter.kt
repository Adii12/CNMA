package com.reea.cnma

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.reea.cnma.models.Movie
import kotlinx.android.synthetic.main.recyclelist_item.view.*

class RecyclerViewAdapter(private val exampleList: LiveData<List<Movie>>?) : RecyclerView.Adapter<RecyclerViewAdapter.MovieHolder>() {
    private var movies : List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclelist_item, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val currentMovie = movies.get(position)
        holder.textView1.text = currentMovie.Title
        holder.textView2.text = currentMovie.Year
    }

    override fun getItemCount() = movies.size

    fun setMovies(movies : List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.testText1
        val textView2: TextView = itemView.testText2
    }
}