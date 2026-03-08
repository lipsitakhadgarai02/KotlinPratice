package com.example.gridlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et1 = findViewById<EditText>(R.id.editText1)
        val et2 = findViewById<EditText>(R.id.editText2)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnClear = findViewById<Button>(R.id.btnclear)

        val tvResult = findViewById<TextView>(R.id.textView)

        btnAdd.setOnClickListener {
            val number1 = et1.text.toString().toInt()
            val number2 = et2.text.toString().toInt()
            tvResult.text = "${number1 + number2}"
        }

        btnSub.setOnClickListener {
            val number1 = et1.text.toString().toInt()
            val number2 = et2.text.toString().toInt()
            tvResult.text = "${number1 - number2}"
        }

        btnMul.setOnClickListener {
            val number1 = et1.text.toString().toInt()
            val number2 = et2.text.toString().toInt()
            tvResult.text = "${number1 * number2}"
        }

        btnDiv.setOnClickListener {
            val number1 = et1.text.toString().toDouble()
            val number2 = et2.text.toString().toDouble()

            if (number2 != 0.0) {
                tvResult.text = "${number1 / number2}"
            } else {
                tvResult.text = "Cannot divide by zero"
            }
        }

        btnClear.setOnClickListener {
            et1.text.clear()
            et2.text.clear()
            tvResult.text = ""
        }
    }
}
