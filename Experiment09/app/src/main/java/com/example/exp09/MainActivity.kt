package com.example.exp09
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnRead = findViewById<Button>(R.id.btnRead)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Save Data
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isNotEmpty()) {

                database.child("Students").push().setValue(name)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
                        etName.text.clear()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show()
                    }

            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        // Read Data
        btnRead.setOnClickListener {

            database.child("Students").get()
                .addOnSuccessListener {

                    val result = StringBuilder()

                    for (child in it.children) {
                        result.append(child.value.toString()).append("\n")
                    }

                    tvResult.text = result.toString()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to read data", Toast.LENGTH_SHORT).show()
                }
        }
    }
}