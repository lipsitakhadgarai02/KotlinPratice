package com.example.musicapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Callback
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofitBuilder.create(ApiInterface::class.java)

        val retrofitData = apiInterface.getData("eminem")

        retrofitData.enqueue(object  : Callback<List<MyData>?> {
            override fun onResponse(
                call: Call<List<MyData>?>, response: Response<List<MyData>?>
            ) {
                TODO("NOT YET IMPLEMENTED")
            }

            override fun onFailure(call: Call<List<MyData>?>, t: Throwable) {
                TODO("NOT YET IMPLEMENTED")
            }
        })

    }
}