package com.example.ooushfitness.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

class RegisterUserResponse {
    @SerializedName("data")
    @Expose
    private var data : LinkedTreeMap<String, Any?>? = null

    @SerializedName("size")
    @Expose
    private var size : Int? = null;

    fun getData(): Any? {
        return this.data;
    }

}