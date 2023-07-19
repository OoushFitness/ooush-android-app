package com.example.ooushfitness.http.retrofit

import android.content.Context
import com.example.ooushfitness.constants.OoushConstants
import com.example.ooushfitness.http.MyCookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    private fun getRedirectInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request: Request = chain.request()
            var response = chain.proceed(request)
            if (response.code() == 307) {
                request = response.header("Location")?.let {
                    request.newBuilder()
                        .url(it)
                        .build()
                }!!
                response = chain.proceed(request)
            }
            response
        }
    }

    private fun getHeaderInterceptor(context: Context?): Interceptor {
        return Interceptor { chain ->
            var request: Request = chain.request()
            val token = context?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)?.getString("token", "")
            request = token?.let {
                request.newBuilder()
                    .addHeader("X-Auth-Token", it).build()
            }!!
            chain.proceed(request)
        }
    }

    /**
     * Replace base url with machine ip found by running 'ipconfig getifaddr en0', port is usually :8080'
     * (This will only work with the android studio emulator)
     * To run the api from both the emulator and a mobile device, generate a tunnel with ngrok by running:
     * 'ngrok http 8080'
     * This will generate a url, replace the base url with this, you may need to visit the link once with
     * the browser to add the abuse interstitial cookie
     **/
    fun getService(clazz: Class<*>?, context: Context?) : Any? {
        val client = OkHttpClient.Builder()
            .addInterceptor(getHeaderInterceptor(context))
            .addInterceptor(getRedirectInterceptor())
            .cookieJar(MyCookieJar())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://6df2-2a02-c7c-60c0-1300-c81a-126a-5691-d54f.ngrok-free.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(clazz);
    }

}