package com.example.ooushfitness.http.service

import com.example.ooushfitness.dto.request.RegisterUserRequest
import com.example.ooushfitness.dto.response.RegisterUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface UserService {

    @POST("users/registerUser")
    fun registerUser(@Body registerUserRequest: RegisterUserRequest): Call<RegisterUserResponse>

}