package com.example.colorchaning

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnhot = findViewById<Button>(R.id.btnhot)
        val btncool = findViewById<Button>(R.id.btncool)
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        btnhot.setOnClickListener {
            linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        }
        btncool.setOnClickListener {
            linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
        }
    }
}
