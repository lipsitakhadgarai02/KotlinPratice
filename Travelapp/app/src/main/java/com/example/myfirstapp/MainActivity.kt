package com.example.myfirstapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentimg = 1
    private lateinit var image: ImageView
    private lateinit var placename: TextView
    private val places = arrayOf("Taj Mahal", "Buddha", "Red Fort", "Lotus Temple", "Train", "Lal Kila")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val next = findViewById<ImageButton>(R.id.btnNext)
        val prev = findViewById<ImageButton>(R.id.btnprev)
        placename = findViewById(R.id.tvname)

        next.setOnClickListener {
            // hide current image
            val idCurrentImgStr = "pic$currentimg"
            val idCurrentImgInt = resources.getIdentifier(idCurrentImgStr, "id", packageName)
            image = findViewById(idCurrentImgInt)
            image.alpha = 0f

            // move to next
            currentimg++
            if (currentimg > 6) currentimg = 1

            // show next image
            val idImgToShowStr = "pic$currentimg"
            val idImgToShowInt = resources.getIdentifier(idImgToShowStr, "id", packageName)
            image = findViewById(idImgToShowInt)
            image.alpha = 1f

            placename.text = places[currentimg - 1]
        }

        prev.setOnClickListener {
            // hide current image
            val idCurrentImgStr = "pic$currentimg"
            val idCurrentImgInt = resources.getIdentifier(idCurrentImgStr, "id", packageName)
            image = findViewById(idCurrentImgInt)
            image.alpha = 0f

            // move to previous
            currentimg--
            if (currentimg < 1) currentimg = 6

            // show previous image
            val idImgToShowStr = "pic$currentimg"
            val idImgToShowInt = resources.getIdentifier(idImgToShowStr, "id", packageName)
            image = findViewById(idImgToShowInt)
            image.alpha = 1f

            placename.text = places[currentimg - 1]
        }
    }
}
