package com.reea.cnma

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.reea.cnma.utils.Util

class TicketsActivity : AppCompatActivity() {
    private lateinit var movieTitle : String
    private lateinit var movieTitleTextView : TextView
    private lateinit var selectDate : Button
    private lateinit var dateTextView: TextView
    private lateinit var selectHour : Spinner
    private lateinit var priceTextView : TextView
    private lateinit var minusButton : Button
    private lateinit var plusButton : Button
    private lateinit var qtyTextView: TextView
    private lateinit var totalPriceTextView: TextView
    private lateinit var confirmOrderButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var rootView : ConstraintLayout
    private var quantity = 1
    private var price : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        movieTitleTextView = findViewById(R.id.titleTextView)
        selectDate = findViewById(R.id.pickDateButton)
        dateTextView = findViewById(R.id.dateTextView)
        selectHour = findViewById(R.id.selectHourSpinner)
        priceTextView = findViewById(R.id.priceTextView)
        minusButton = findViewById(R.id.minusButton)
        plusButton = findViewById(R.id.plusButton)
        qtyTextView = findViewById(R.id.qtyTextView)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)
        confirmOrderButton = findViewById(R.id.confirmOrderButton)
        backButton = findViewById(R.id.backButton)
        rootView = findViewById(R.id.constraintLayout)

        movieTitle = intent.getStringExtra("movieTitle").toString()

        movieTitleTextView.text = movieTitle

        val utils = Util()
        selectDate.setOnClickListener {
            utils.pickDate(dateTextView, this@TicketsActivity)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, utils.getHours())
        selectHour.adapter = adapter

        price = utils.getRandomPrice()
        priceTextView.text = price.toString()

        qtyTextView.text = quantity.toString()

        var totalPrice = price * quantity
        totalPriceTextView.text = totalPrice.toString()

        plusButton.setOnClickListener {
            quantity++
            qtyTextView.text = quantity.toString()

            totalPrice = price * quantity
            totalPriceTextView.text = totalPrice.toString()
        }

        minusButton.setOnClickListener {
            quantity--
            qtyTextView.text = quantity.toString()

            totalPrice = price * quantity
            totalPriceTextView.text = totalPrice.toString()
        }

        confirmOrderButton.setOnClickListener {
            if(dateTextView.text == "") {
                Toast.makeText(this, R.string.select_date_error, Toast.LENGTH_SHORT).show()
                dateTextView.requestFocus()
            }
            else if(selectHour.selectedItem.toString() == "Select hour"){
                Toast.makeText(this, R.string.select_hour_error, Toast.LENGTH_SHORT).show()
                selectHour.requestFocus()
            }
            else {
                Snackbar.make(rootView, R.string.successfully_bought, Snackbar.LENGTH_LONG).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}