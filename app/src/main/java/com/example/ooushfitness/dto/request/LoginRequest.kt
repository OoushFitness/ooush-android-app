package com.example.ooushfitness.dto.request

class LoginRequest {
    private var success = false
    private var userId = 0
    private var email: String? = null
    private var userName: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var location: String? = null
    private var token: String? = null
    private var loginMessage: String? = null
    private var authenticated = false
    private var weightDenomination: String? = null

    fun LoginResponse() {
        authenticated = false
    }

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