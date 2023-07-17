package com.example.ooushfitness.http

import com.example.ooushfitness.dto.request.LoginRequest
import com.example.ooushfitness.dto.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TestService {

    @GET("/users/GreenA89/repos")
    fun getTest(): Call<ResponseBody>

    @POST("/auth/login")
    fun getLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

}