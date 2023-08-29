package com.example.ooushfitness.http.service

import com.example.ooushfitness.dto.response.WorkoutDayResponse
import retrofit2.Call
import retrofit2.http.GET

interface DashboardWorkoutService {

    @GET("/workouts/get-dashboard-workouts")
    fun getDashboardWorkouts(): Call<WorkoutDayResponse>

}