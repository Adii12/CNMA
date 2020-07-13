package com.reea.cnma

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reea.cnma.models.Movie
import com.reea.cnma.viewModels.MovieDetailsScreenViewModel
import com.reea.cnma.viewModels.MovieDetailsViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var currentMovie : Movie
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
    private lateinit var movieTitle : String
    private lateinit var genreTextView : TextView
    private lateinit var ratingBar : RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

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

        currentMovie = intent.getSerializableExtra("currentMovie") as Movie
        val cm  = currentMovie

        if(cm != null){
            movieTitle = currentMovie.Title
        }

        val model = ViewModelProvider(this@MovieDetailsActivity, MovieDetailsViewModelFactory(movieTitle, application)).get(MovieDetailsScreenViewModel::class.java)

        model.getMovieDetail().observe(this, Observer<Movie> { movie ->
            titleTextView.text = movie.Title
            imdbRatingTextView.text = movie.ImdbRating
            var rating  = movie.ImdbRating.toFloat()
            ratingBar.rating = rating / 2
            genreTextView.text = movie.Genre
            yearTextView.text = movie.Year
            ratedTextView.text = movie.Rated
            runtimeTextView.text = movie.Runtime
            plotTextView.text = movie.Plot
            actorsTextView.text = movie.Actors
            directorsTextView.text = movie.Director
            Picasso.get().load(movie.Poster).into(poster)
        })

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}