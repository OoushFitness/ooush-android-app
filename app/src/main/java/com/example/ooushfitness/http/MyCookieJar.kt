package com.example.ooushfitness.http

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl


class MyCookieJar: CookieJar {
    private val cookieStore = HashMap<String, List<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        cookieStore[url.host()] = cookies;
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = cookieStore[url.host()]
        return cookies?: ArrayList()
    }
}