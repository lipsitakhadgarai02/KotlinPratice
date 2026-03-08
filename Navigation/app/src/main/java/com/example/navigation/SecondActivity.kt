package com.example.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tv1 = findViewById<TextView>(R.id.tv1)
        val tv2 = findViewById<TextView>(R.id.tv2)
        val tv3 = findViewById<TextView>(R.id.tv3)

        val name = intent.getStringExtra("name")
        val roll = intent.getStringExtra("roll")
        val phone = intent.getStringExtra("phone")

        tv1.text = "Name is: $name"
        tv2.text = "Roll Number is: $roll"
        tv3.text = "Phone Number is: $phone"
    }
}
