package com.example.ooushfitness.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ooushfitness.R
import com.example.ooushfitness.databinding.FragmentFirstBinding
import com.example.ooushfitness.dto.request.LoginRequest
import com.example.ooushfitness.dto.response.LoginResponse
import com.example.ooushfitness.http.retrofit.RetrofitBuilder
import com.example.ooushfitness.http.service.AuthService
import com.example.ooushfitness.storage.SessionUtils
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var authService: AuthService
    private lateinit var spinner: ProgressBar

    private var _binding: FragmentFirstBinding? = null
    private var retrofitBuilder : RetrofitBuilder = RetrofitBuilder()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        authService = retrofitBuilder.getService(AuthService::class.java, context) as AuthService
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.INVISIBLE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            processLogin()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun processLogin() {
        binding.progressBar.visibility = View.VISIBLE
        val loginRequest = LoginRequest();
        loginRequest.setUserName(binding.editTextTextEmailAddress.text.toString())
        loginRequest.setPassword(binding.editTextTextPassword.text.toString())
        authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                processLoginResponse(retrieveLoginResponseData(response))
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        });
    }

    private fun retrieveLoginResponseData(response: Response<LoginResponse>): LoginResponse.LoginResponseData? {
        val loginResponseData: LoginResponse.LoginResponseData? = if (response.isSuccessful) {
            response.body()?.getData()
        } else {
            Gson().fromJson(response.errorBody()?.charStream(), LoginResponse::class.java).getData()
        }
        return loginResponseData
    }

    private fun processLoginResponse(loginResponse: LoginResponse.LoginResponseData?) {
        if (loginResponse != null) {
            if (loginResponse.isSuccess()) {
                SessionUtils.storeData(activity, "token", loginResponse.getToken())
                SessionUtils.storeData(activity, "authenticated", true)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                binding.loginText.text = loginResponse.getLoginMessage()
                binding.progressBar.visibility = View.INVISIBLE
                Executors.newSingleThreadScheduledExecutor().schedule({
                    binding.loginText.text = null
                }, 7, TimeUnit.SECONDS)
            }
        }
        binding.progressBar.visibility = View.INVISIBLE
    }
}