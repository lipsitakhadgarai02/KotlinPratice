package com.example.firebasedemoexp08

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var firstStudentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().reference.child("Students")

        val etName = findViewById<EditText>(R.id.etName)
        val btnCreate = findViewById<Button>(R.id.btnCreate)
        val btnRead = findViewById<Button>(R.id.btnRead)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // =====================
        // CREATE
        // =====================
        btnCreate.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isNotEmpty()) {
                val id = database.push().key!!
                val student = Student(id, name)

                database.child(id).setValue(student)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Student Created", Toast.LENGTH_SHORT).show()
                        etName.text.clear()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to create: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        // =====================
        // READ
        // =====================
        btnRead.setOnClickListener {
            firstStudentId = null // Reset
            database.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val result = StringBuilder()
                    for (child in snapshot.children) {
                        try {
                            val student = child.getValue(Student::class.java)
                            if (student != null) {
                                result.append("ID: ${student.id}  Name: ${student.name}\n")
                                if (firstStudentId == null) {
                                    firstStudentId = student.id
                                }
                            }
                        } catch (e: Exception) {
                            // If a record is a String instead of an object, it lands here
                            result.append("Invalid Record at ${child.key}: ${child.value}\n")
                        }
                    }
                    tvResult.text = result.toString()
                } else {
                    tvResult.text = "No records found in database."
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Read failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // =====================
        // UPDATE (First Record)
        // =====================
        btnUpdate.setOnClickListener {
            if (firstStudentId != null) {
                database.child(firstStudentId!!)
                    .child("name")
                    .setValue("Updated Name")
                    .addOnSuccessListener {
                        Toast.makeText(this, "Updated First Record", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Read Data First to get a record", Toast.LENGTH_SHORT).show()
            }
        }

        // =====================
        // DELETE (First Record)
        // =====================
        btnDelete.setOnClickListener {
            if (firstStudentId != null) {
                database.child(firstStudentId!!)
                    .removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Deleted First Record", Toast.LENGTH_SHORT).show()
                        firstStudentId = null
                    }
            } else {
                Toast.makeText(this, "Read Data First to get a record", Toast.LENGTH_SHORT).show()
            }
        }
    }
}