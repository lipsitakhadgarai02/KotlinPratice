package com.example.firebasestoragedemo

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasestoragedemo.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var imageUri: Uri? = null
    private val storageRef: StorageReference =
        FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpload.setOnClickListener {
            uploadToFirebase()
        }
    }

    private fun uploadToFirebase() {

        if (imageUri == null) {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show()
            return
        }

        val fileRef = storageRef.child("images/${System.currentTimeMillis()}.jpg")

        binding.progressBar.progress = 0
        binding.progressBar.visibility = View.VISIBLE
        binding.btnUpload.isEnabled = false

        fileRef.putFile(imageUri!!)
            .addOnProgressListener { taskSnapshot ->
                val progress =
                    (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount)
                binding.progressBar.progress = progress.toInt()
            }
            .addOnSuccessListener {

                binding.progressBar.visibility = View.GONE
                binding.btnUpload.isEnabled = true

                Toast.makeText(this, "Upload Successful!", Toast.LENGTH_LONG).show()

                fileRef.downloadUrl.addOnSuccessListener { url ->
                    android.util.Log.d("FirebaseURL", url.toString())
                }

                binding.ivPreview.setImageResource(android.R.drawable.ic_menu_gallery)
                imageUri = null
            }
            .addOnFailureListener { e ->

                binding.progressBar.visibility = View.GONE
                binding.btnUpload.isEnabled = true

                Toast.makeText(this, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}