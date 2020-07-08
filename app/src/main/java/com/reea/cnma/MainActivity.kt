package com.reea.cnma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.local.DatabaseRepository
import com.reea.cnma.repository.remote.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var db : DatabaseRepository? = null
    private lateinit var api : MovieRepository
    private var moviesList = db?.getAllMovies()
    private lateinit var adapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = RecyclerViewAdapter(moviesList)
        recyclerViewItems.adapter = adapter
        recyclerViewItems.layoutManager = LinearLayoutManager(this)
        recyclerViewItems.setHasFixedSize(true)

        db = DatabaseRepository(application)
        api = MovieRepository()

        /*var movie : MutableLiveData<Movie>
        api.getMovie("Batman").observe(this, object : Observer<Movie> {
            override fun onChanged(movie : Movie?) {
                println(movie?.Title)
                db?.insert(movie!!)
            }
        })*/

        var movies = db?.getAllMovies()?.observe(this, object : Observer<List<Movie>> {
            override fun onChanged(moviesList: List<Movie>?) {
                if (moviesList != null) {
                    adapter.setMovies(moviesList)
                }
            }
        })
    }
}