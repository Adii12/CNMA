package com.reea.cnma

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reea.cnma.adapters.GridSpacingItemDecoration
import com.reea.cnma.adapters.HomeScreenAdapter
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.local.DatabaseRepository
import com.reea.cnma.repository.remote.MovieRepository
import com.reea.cnma.viewModels.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var db : DatabaseRepository? = null
    private lateinit var api : MovieRepository
    private var moviesList = db?.getAllMovies()
    private lateinit var adapter: HomeScreenAdapter
    private lateinit var bottomNav : BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNavigationBar)
        adapter = HomeScreenAdapter(this@MainActivity)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.setHasFixedSize(true)

        var model = ViewModelProvider(this@MainActivity).get(HomeScreenViewModel::class.java)
        model.getMovies()?.observe(this, Observer<List<Movie>> { movies -> adapter.setMovies(movies) })

        bottomNav.selectedItemId = R.id.navigation_home
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_favorites -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    overridePendingTransition(0,0)
                }

                R.id.navigation_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    overridePendingTransition(0,0)
                }
            }
            true
        }
    }


}
