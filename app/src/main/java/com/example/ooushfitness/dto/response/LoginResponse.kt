package com.example.ooushfitness.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("data")
    @Expose
    private var data : LoginResponseData? = null

    @SerializedName("size")
    @Expose
    private var size : Int? = null;

    fun getData(): LoginResponseData? {
        return this.data;
    }

    fun getSize(): Int? {
        return this.size;
    }

    class LoginResponseData {
        @SerializedName("success")
        @Expose
        private var success = false

        @SerializedName("userId")
        @Expose
        private var userId = 0

        @SerializedName("email")
        @Expose
        private var email: String? = null

        @SerializedName("userName")
        @Expose
        private var userName: String? = null

        @SerializedName("firstName")
        @Expose
        private var firstName: String? = null

        @SerializedName("lastName")
        @Expose
        private var lastName: String? = null

        @SerializedName("location")
        @Expose
        private var location: String? = null

        @SerializedName("token")
        @Expose
        private var token: String? = null

        @SerializedName("loginMessage")
        @Expose
        private var loginMessage: String? = null

        @SerializedName("authenticated")
        @Expose
        private var authenticated = false

        @SerializedName("weightDenomination")
        @Expose
        private var weightDenomination: String? = null

        fun isSuccess(): Boolean {
            return success
        }

        fun setSuccess(success: Boolean) {
            this.success = success
        }

        fun getUserId(): Int {
            return userId
        }

        fun setUserId(userId: Int) {
            this.userId = userId
        }

        fun getEmail(): String? {
            return email
        }

        fun setEmail(email: String?) {
            this.email = email
        }

        fun getUserName(): String? {
            return userName
        }

        fun setUserName(userName: String?) {
            this.userName = userName
        }

        fun getFirstName(): String? {
            return firstName
        }

        fun setFirstName(firstName: String?) {
            this.firstName = firstName
        }

        fun getLastName(): String? {
            return lastName
        }

        fun setLastName(lastName: String?) {
            this.lastName = lastName
        }

        fun getLocation(): String? {
            return location
        }

        fun setLocation(location: String?) {
            this.location = location
        }

        fun getToken(): String? {
            return token
        }

        fun setToken(token: String?) {
            this.token = token
        }

        fun getLoginMessage(): String? {
            return loginMessage
        }

        fun setLoginMessage(loginMessage: String?) {
            this.loginMessage = loginMessage
        }

        fun isAuthenticated(): Boolean {
            return authenticated
        }

        fun setAuthenticated(authenticated: Boolean) {
            this.authenticated = authenticated
        }

        fun getWeightDenomination(): String? {
            return weightDenomination
        }

        fun setWeightDenomination(weightDenomination: String?) {
            this.weightDenomination = weightDenomination
        }
    }

}