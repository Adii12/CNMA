package com.reea.cnma

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reea.cnma.adapters.FavoritesScreenAdapter
import com.reea.cnma.adapters.GridSpacingItemDecoration
import com.reea.cnma.models.Movie
import com.reea.cnma.viewModels.FavoritesScreenViewModel
import com.reea.cnma.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var adapter : FavoritesScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        bottomNav = findViewById(R.id.bottomNavigationBar)
        adapter = FavoritesScreenAdapter(this@FavoritesActivity)
        recyclerView.adapter=adapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.addItemDecoration(GridSpacingItemDecoration(3,50, true))
        recyclerView.setHasFixedSize(true)

        var model = ViewModelProvider(this@FavoritesActivity, ViewModelFactory("a", application, this@FavoritesActivity)).get(FavoritesScreenViewModel::class.java)
        model.getFavoriteMovies()?.observe(this, Observer<List<Movie>> { favorites -> adapter.setFavorites(favorites)})

        bottomNav.selectedItemId = R.id.navigation_favorites
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
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