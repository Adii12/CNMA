package com.reea.cnma

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.local.DatabaseRepository
import com.reea.cnma.viewModels.MovieDetailsScreenViewModel
import com.reea.cnma.viewModels.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var currentMovieTitle : String
    private lateinit var titleTextView : TextView
    private lateinit var yearTextView : TextView
    private lateinit var ratedTextView : TextView
    private lateinit var runtimeTextView : TextView
    private lateinit var plotTextView : TextView
    private lateinit var actorsTextView : TextView
    private lateinit var directorsTextView : TextView
    private lateinit var poster : ImageView
    private lateinit var backButton : ImageButton
    private lateinit var imdbRating : TextView
    private lateinit var genreTextView : TextView
    private lateinit var ratingBar : RatingBar
    private lateinit var favoriteButton : ToggleButton
    private lateinit var actualMovie : Movie
    private lateinit var buyTicketButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val db = DatabaseRepository(application, this@MovieDetailsActivity)

        titleTextView = findViewById(R.id.TitleTextView)
        yearTextView = findViewById(R.id.YearTextView)
        ratedTextView = findViewById(R.id.RatedTextView)
        genreTextView = findViewById(R.id.genreTextView)
        runtimeTextView = findViewById(R.id.RuntimeTextView)
        plotTextView = findViewById(R.id.PlotTextView)
        actorsTextView = findViewById(R.id.ActorsTextView)
        directorsTextView = findViewById(R.id.DirectorsTextView)
        imdbRating = findViewById(R.id.imdbRatingTextView)
        poster = findViewById(R.id.PosterImageView)
        ratingBar = findViewById(R.id.ratingBar)
        favoriteButton = findViewById(R.id.addToFavoritesButton)
        buyTicketButton = findViewById(R.id.buyTicketButton)


        currentMovieTitle = intent.getStringExtra("currentMovieTitle").toString()
        val cmt  = currentMovieTitle

        if(cmt == null){
            finish()
        }

        val model = ViewModelProvider(this@MovieDetailsActivity, ViewModelFactory(currentMovieTitle, application, this@MovieDetailsActivity)).get(MovieDetailsScreenViewModel::class.java)

        model.getMovieDetail().observe(this, Observer<Movie> { movie ->
            titleTextView.text = movie.Title
            imdbRatingTextView.text = movie.ImdbRating
            val rating  = movie.ImdbRating.toFloat()
            ratingBar.rating = rating / 2
            genreTextView.text = movie.Genre
            yearTextView.text = movie.Year
            ratedTextView.text = movie.Rated
            runtimeTextView.text = movie.Runtime
            plotTextView.text = movie.Plot
            actorsTextView.text = movie.Actors
            directorsTextView.text = movie.Director
            Picasso.get().load(movie.Poster).into(poster)

            actualMovie = movie
        })
        favoriteButton.isChecked = db.getMovie(currentMovieTitle) != null

        favoriteButton.setOnCheckedChangeListener { _ , isChecked ->
            if(isChecked){
                db.insert(actualMovie)
            } else {
                db.deleteMovie(actualMovie.Title)
            }
        }

        buyTicketButton.setOnClickListener {
            val buyTickets = Intent(this, TicketsActivity::class.java)
            buyTickets.putExtra("movieTitle", cmt)
            startActivity(buyTickets)
        }

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}