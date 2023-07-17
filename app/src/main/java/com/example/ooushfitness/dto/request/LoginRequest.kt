package com.example.ooushfitness.dto.request

class LoginRequest {
    private var userName: String? = null
    private var password: String? = null

    fun getUserName(): String? {
        return userName
    }

    fun setUserName(userName: String?) {
        this.userName = userName
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }
}