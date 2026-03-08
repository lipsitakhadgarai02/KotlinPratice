package com.example.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name)
        val roll = findViewById<EditText>(R.id.roll)
        val phone = findViewById<EditText>(R.id.phone)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            val msg1 = name.text.toString()
            val msg2 = roll.text.toString()
            val msg3 = phone.text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", msg1)
            intent.putExtra("roll", msg2)
            intent.putExtra("phone", msg3)
            startActivity(intent)
        }
    }
}
