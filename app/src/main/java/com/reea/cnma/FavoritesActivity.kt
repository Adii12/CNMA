package com.reea.cnma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
    }
    private val navigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val mainIntent = Intent(this@FavoritesActivity, MainActivity::class.java)
                startActivity(mainIntent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                val searchIntent = Intent(this@FavoritesActivity, SearchActivity::class.java)
                startActivity(searchIntent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}