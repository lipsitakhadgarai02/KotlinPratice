package com.example.crud

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "StudentDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE Students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT
            ) 
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Students")
        onCreate(db)
    }

    // CREATE (Insert)
    fun insertStudent(student: Student): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", student.name)
        val result = db.insert("Students", null, values)
        return result != -1L
    }

    // READ (Select All)
    fun getAllStudents(): List<Student> {
        val list = mutableListOf<Student>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Students", null)

        if (cursor.moveToFirst()) {
            do {
                val student = Student(
                    id = cursor.getInt(0),
                    name = cursor.getString(1)
                )
                list.add(student)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    // UPDATE
    fun updateStudent(student: Student): Int {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", student.name)
        return db.update(
            "Students",
            values,
            "id=?",
            arrayOf(student.id.toString())
        )
    }

    // DELETE
    fun deleteStudent(id: Int): Int {
        val db = writableDatabase
        return db.delete(
            "Students",
            "id=?",
            arrayOf(id.toString())
        )
    }
}
