//st10478143 tshiamo makitla
package com.example.musicplaylistmanager

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val listView = findViewById<ListView>(R.id.listView)
        val btnShowAll = findViewById<Button>(R.id.btnShowAll)
        val btnShowAverage = findViewById<Button>(R.id.btnShowMultiple) // Renamed for clarity
        val btnBack = findViewById<Button>(R.id.btnBack)

        fun buildList(): List<String> {
            val list = mutableListOf<String>()
            for (i in MainActivity.songtitle.indices) {
                list.add("${MainActivity.songtitle[i]} (${MainActivity.Artistname[i]}, Rt: ${MainActivity.Rating[i]}) - ${MainActivity.comments[i]}")
            }
            return list
        }

        // Show all items immediately when screen opens
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, buildList())
        listView.adapter = adapter

        btnShowAll.setOnClickListener {
            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, buildList())
        }

        btnShowAverage.setOnClickListener {
            if (MainActivity.Rating.isNotEmpty()) {
                // Calculate average rating using a loop
                var sum = 0.0
                var count = 0

                for (rating in MainActivity.Rating) {
                    sum += rating
                    count++
                }

                val average = sum / count
                Toast.makeText(
                    this,
                    "Average Rating: ${"%.2f".format(average)}",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this, "No ratings available", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
