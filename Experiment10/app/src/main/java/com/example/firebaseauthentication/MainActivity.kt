package com.example.firebaseauthentication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        // Check if user already logged in
        if (auth.currentUser != null) {
            tvStatus.text = "Already Logged In: ${auth.currentUser?.email}"
        }

        // -------------------
        // SIGN UP
        // -------------------
        btnSignup.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter Email & Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    tvStatus.text = "Signup Success: ${auth.currentUser?.email}"
                }
                .addOnFailureListener {
                    tvStatus.text = "Signup Failed: ${it.message}"
                }
        }

        // -------------------
        // LOGIN
        // -------------------
        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter Email & Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    tvStatus.text = "Login Success: ${auth.currentUser?.email}"
                }
                .addOnFailureListener {
                    tvStatus.text = "Login Failed: ${it.message}"
                }
        }

        // -------------------
        // LOGOUT
        // -------------------
        btnLogout.setOnClickListener {
            auth.signOut()
            tvStatus.text = "Logged Out"
        }
    }
}