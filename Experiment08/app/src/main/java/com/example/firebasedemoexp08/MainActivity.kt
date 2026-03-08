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

                Toast.makeText(this, "Student Created", Toast.LENGTH_SHORT).show()
                etName.text.clear()
            }
        }

        // =====================
        // READ
        // =====================
        btnRead.setOnClickListener {

            database.get().addOnSuccessListener { snapshot ->

                if (snapshot.exists()) {

                    val result = StringBuilder()

                    for (child in snapshot.children) {
                        val student = child.getValue(Student::class.java)
                        result.append("ID: ${student?.id}  Name: ${student?.name}\n")

                        // Store first record ID for update/delete
                        if (firstStudentId == null) {
                            firstStudentId = student?.id
                        }
                    }

                    tvResult.text = result.toString()
                }
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

                Toast.makeText(this, "Updated First Record", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Read Data First", Toast.LENGTH_SHORT).show()
            }
        }

        // =====================
        // DELETE (First Record)
        // =====================
        btnDelete.setOnClickListener {

            if (firstStudentId != null) {

                database.child(firstStudentId!!)
                    .removeValue()

                Toast.makeText(this, "Deleted First Record", Toast.LENGTH_SHORT).show()

                firstStudentId = null
            } else {
                Toast.makeText(this, "Read Data First", Toast.LENGTH_SHORT).show()
            }
        }
    }
}