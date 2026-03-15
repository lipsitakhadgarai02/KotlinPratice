package com.example.musicapp

data class MyData(
    val ads: Ads,
    val country: String,
    val country_iso: String,
    val has_podcasts: Boolean,
    val hosts: Hosts,
    val offers: List<Any>,
    val `open`: Boolean,
    val pop: String,
    val upload_token: String,
    val upload_token_lifetime: Int,
    val user_token: Any
)