package com.example.crud
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        val etName = findViewById<EditText>(R.id.etName)
        val btnCreate = findViewById<Button>(R.id.btnCreate)
        val btnRead = findViewById<Button>(R.id.btnRead)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // CREATE Logic
        btnCreate.setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                etName.error = "Enter name"
                return@setOnClickListener
            }
            val success = dbHelper.insertStudent(Student(name = name))
            if (success) {
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show()
                etName.text.clear()
            } else {
                Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show()
            }
        }

        // READ Logic
        btnRead.setOnClickListener {
            val list = dbHelper.getAllStudents()
            val result = StringBuilder()
            for (s in list) {
                result.append("ID: ${s.id} Name: ${s.name}\n")
            }
            tvResult.text = result.toString()
        }

        // UPDATE Logic (First Record)
        btnUpdate.setOnClickListener {
            val list = dbHelper.getAllStudents()
            if (list.isNotEmpty()) {
                val student = list[0]
                student.name = "Updated Name"
                dbHelper.updateStudent(student)
                Toast.makeText(this, "Updated First Record", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
        }

        // DELETE Logic (First Record)
        btnDelete.setOnClickListener {
            val list = dbHelper.getAllStudents()
            if (list.isNotEmpty()) {
                dbHelper.deleteStudent(list[0].id)
                Toast.makeText(this, "Deleted First Record", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
