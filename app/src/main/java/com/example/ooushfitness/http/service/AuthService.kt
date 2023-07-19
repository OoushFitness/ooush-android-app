package com.example.ooushfitness.http.service

import com.example.ooushfitness.dto.request.LoginRequest
import com.example.ooushfitness.dto.response.LoginResponse
import com.example.ooushfitness.dto.response.LogoutResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

public interface AuthService {

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("/auth/logout")
    fun logout(): Call<LogoutResponse>

}