package com.example.ooushfitness.http.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    private var retrofit: Retrofit = Retrofit.Builder()
        // Replace base url with machine ip found by running 'ipconfig getifaddr en0', port is usually :8080'
        .baseUrl("http://10.12.3.92:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    fun getService(clazz: Class<*>?) : Any? {
        return retrofit.create(clazz);
    }

}