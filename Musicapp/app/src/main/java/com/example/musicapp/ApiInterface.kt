package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("x-rapidapi-key\", \"3184ab04d9msh801a390d0262e1ap160b20jsn09c45174da17" , "x-rapidapi-host\", \"deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(
        @Query("q") query: String
    ): Call<MyData>

}