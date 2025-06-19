//st10478143 tshiamo makitla
package com.example.musicplaylistmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {

    companion object {
        val songtitle = mutableListOf<String>()
        val Artistname = mutableListOf<String>()
        val Rating = mutableListOf<Float>()
        val comments = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songTitleInput = findViewById<EditText>(R.id.editsongtitle)
        val artistNameInput = findViewById<EditText>(R.id.editArtistname)
        val ratingInput = findViewById<EditText>(R.id.editRating)
        val commentInput = findViewById<EditText>(R.id.editComment)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val name = songTitleInput.text.toString().trim()
            val artist = artistNameInput.text.toString().trim()
            val ratingStr = ratingInput.text.toString().trim()
            val comment = commentInput.text.toString().trim()

            if (name.isEmpty() || artist.isEmpty() || ratingStr.isEmpty() || comment.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val rating = ratingStr.toFloatOrNull()
            if (rating == null || rating !in 1.0..5.0) {
                Toast.makeText(this, "Rating must be a number between 1 and 5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            songtitle.add(name)
            Artistname.add(artist)
            Rating.add(rating)
            comments.add(comment)

            Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show()


            songTitleInput.text.clear()
            artistNameInput.text.clear()
            ratingInput.text.clear()
            commentInput.text.clear()
        }

        findViewById<Button>(R.id.btnViewPlaylist).setOnClickListener {
            startActivity(Intent(this, DetailedActivity::class.java))
        }

        findViewById<Button>(R.id.btnExit).setOnClickListener {
            finish()
        }
    }
}
