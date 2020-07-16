package com.reea.cnma

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reea.cnma.adapters.SearchScreenAdapter
import com.reea.cnma.models.Movie
import com.reea.cnma.viewModels.SearchScreenViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var adapter : SearchScreenAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchBar : EditText
    private lateinit var searchButton : ImageButton
    private lateinit var searchMovieTitle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        bottomNav = findViewById(R.id.bottomNavigationBar)
        recyclerView = findViewById(R.id.recyclerView)
        searchBar = findViewById(R.id.searchBar)
        searchButton = findViewById(R.id.searchButton)

        adapter = SearchScreenAdapter(this@SearchActivity)

        recyclerView.adapter = adapter
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val model = ViewModelProvider(this).get(SearchScreenViewModel::class.java)

        searchButton.setOnClickListener {
            searchMovieTitle = searchBar.text.toString()
            model.searchMovie(searchMovieTitle)?.observe(this, Observer<List<Movie>> {
                    searched->searched?.let{ adapter.setSearched(searched) }
            })
        }

        bottomNav.selectedItemId = R.id.navigation_search
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0,0)
                }

                R.id.navigation_favorites -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    overridePendingTransition(0,0)
                }
            }
            true
        }
    }
}