package com.reea.cnma.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class Util {

    fun pickDate(dateTextView: TextView, context: Context) {
        val calendar = Calendar.getInstance()
        val mYear = calendar.get(Calendar.YEAR)
        val mMonth = calendar.get(Calendar.MONTH)
        val mDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker : DatePickerDialog = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { _, year, month, day -> dateTextView.text = "$day / ${month + 1} / $year" }, mYear, mMonth, mDay)
        datePicker.show()
    }

    private var hoursArray : ArrayList<String> = ArrayList()


    fun getHours() : ArrayList<String> {
        hoursArray.add("Select hour")
        hoursArray.add("11:00")
        hoursArray.add("13:00")
        hoursArray.add("15:00")
        hoursArray.add("17:00")
        hoursArray.add("19:00")
        hoursArray.add("21:00")

        return hoursArray
    }


    fun getRandomPrice() : Int{
        return (15..50).random()
    }
}