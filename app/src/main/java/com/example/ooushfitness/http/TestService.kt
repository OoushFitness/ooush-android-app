package com.example.ooushfitness.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface TestService {

    @GET("/users/GreenA89/repos")
    fun getTest(): Call<ResponseBody>

}