package com.reea.cnma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
    private val navigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val mainIntent = Intent(this@SearchActivity, MainActivity::class.java)
                startActivity(mainIntent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val favoritesIntent = Intent(this@SearchActivity, FavoritesActivity::class.java)
                startActivity(favoritesIntent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}