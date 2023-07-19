package com.example.ooushfitness.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LogoutResponse {

    @SerializedName("data")
    @Expose
    private var data : LogoutResponse.LogoutResponseData? = null

    @SerializedName("size")
    @Expose
    private var size : Int? = null;

    fun getData(): LogoutResponse.LogoutResponseData? {
        return this.data;
    }

    fun getSize(): Int? {
        return this.size;
    }

    class LogoutResponseData {
        private var success = false

        fun getSuccess(): Boolean {
            return success
        }

        fun setSuccess(success: Boolean) {
            this.success = success
        }
    }

}