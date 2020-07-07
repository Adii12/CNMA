package com.reea.cnma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclelist_item.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var exampleList = generateItems(10)
        recyclerViewItems.adapter = RecyclerViewAdapter(exampleList)
        recyclerViewItems.layoutManager = LinearLayoutManager(this)
        recyclerViewItems.setHasFixedSize(true)
    }

    private fun generateItems(size: Int) : List<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val item = ExampleItem("item $i", "item $i")
            list += item
        }
        return list
    }
}