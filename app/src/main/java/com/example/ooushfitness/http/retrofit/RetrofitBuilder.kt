package com.example.ooushfitness.http.retrofit

import retrofit2.Retrofit




class RetrofitBuilder {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .build();

    fun getService(clazz: Class<*>?) : Any? {
        return retrofit.create(clazz);
    }

}