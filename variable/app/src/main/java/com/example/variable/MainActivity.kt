package com.example.variable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link TextView AFTER setContentView
        val textViewOutput = findViewById<TextView>(R.id.textView)

        // Variables
        val student: String = "Lipsita"
        val mark: Int = 95
        val attendance: Double = 76.9
        val grade: Char = '0'
        val pass: Boolean = mark >= 60 && (attendance >= 75)

        // StringBuilder
        val result = StringBuilder()
        result.append("Student Name: $student\n")
        result.append("Grade: $grade\n")
        result.append("Pass: $pass\n")
        result.append("Attendance: $attendance\n")
        result.append("Mark: $mark\n")

        if (pass) {
            result.append("Eligibility: Eligible")
        } else {
            result.append("Eligibility: Not Eligible")
        }

        // Display output
        textViewOutput.text = result.toString()
    }
}
