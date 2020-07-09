package com.reea.cnma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.reea.cnma.adapters.GridSpacingItemDecoration
import com.reea.cnma.adapters.RecyclerViewAdapter
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.local.DatabaseRepository
import com.reea.cnma.repository.remote.MovieRepository
import com.reea.cnma.viewModels.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var db : DatabaseRepository? = null
    private lateinit var api : MovieRepository
    private var moviesList = db?.getAllMovies()
    private lateinit var adapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(GridSpacingItemDecoration(3,50,true))
        recyclerView.layoutManager = GridLayoutManager(this,3)
        recyclerView.setHasFixedSize(true)

        var model = ViewModelProvider(this@MainActivity).get(HomeScreenViewModel::class.java)
        model.getMovies()?.observe(this, Observer<List<Movie>> { movies -> adapter.setMovies(movies) })
    }
}