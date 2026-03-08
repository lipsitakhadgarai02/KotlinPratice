package com.example.myfirstapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var currentimg = 1
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val next = findViewById<ImageButton>(R.id.btnNext)
        val prev = findViewById<ImageButton>(R.id.btnprev)


        next.setOnClickListener {
//      i want to go next image
            var idCurrentImgStr = "pic$currentimg"
//            i have to get the integer address associated with each view
            var idCurrentImgInt =
                this.resources.getIdentifier(idCurrentImgStr, "id", this.packageName)
            image = findViewById(idCurrentImgInt)
            image.alpha = 0f

            currentimg++
            if (currentimg > 6) {
                currentimg = 1
            }
            var idImgToShowStr = "pic$currentimg"
            var idImgToShowInt =
                this.resources.getIdentifier(idImgToShowStr, "id", this.packageName)
            image = findViewById(idImgToShowInt)
            image.alpha = 1f

        }
        prev.setOnClickListener {
//      i want to go previous image
            var idCurrentImgStr = "pic$currentimg"
//            i have to get the integer address associated with each view
            var idCurrentImgInt =
                this.resources.getIdentifier(idCurrentImgStr, "id", this.packageName)
            image = findViewById(idCurrentImgInt)
            image.alpha = 0f

            currentimg--
            if (currentimg < 1) {
                currentimg = 6
            }
            var idImgToShowStr = "pic$currentimg"
            var idImgToShowInt =
                this.resources.getIdentifier(idImgToShowStr, "id", this.packageName)
            image = findViewById(idImgToShowInt)
            image.alpha = 1f

        }
    }
}