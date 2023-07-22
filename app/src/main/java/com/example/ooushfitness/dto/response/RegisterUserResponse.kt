package com.example.ooushfitness.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterUserResponse {
    @SerializedName("data")
    @Expose
    private var data : String? = null

    @SerializedName("size")
    @Expose
    private var size : Int? = null;

    fun getData(): String? {
        return this.data;
    }

    fun getSize(): Int? {
        return this.size;
    }
}