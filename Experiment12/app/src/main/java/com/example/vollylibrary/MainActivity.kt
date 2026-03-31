package com.example.vollylibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonArrayRequest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/users"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                val users = StringBuilder()
                for (i in 0 until response.length()) {
                    val user = response.getJSONObject(i)
                    val name = user.getString("name")
                    users.append(name).append("\n")
                    Log.d("USER_NAME", name)
                }
                textView.text = users.toString()
            },
            { error ->
                textView.text = "Error: ${error.message}"
            }
        )
        queue.add(request)
    }
}