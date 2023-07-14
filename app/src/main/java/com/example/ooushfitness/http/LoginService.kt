package com.example.ooushfitness.http

import com.example.ooushfitness.dto.request.LoginRequest
import com.example.ooushfitness.dto.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface LoginService {

    @POST("auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

}