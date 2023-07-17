package com.example.ooushfitness.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ooushfitness.databinding.FragmentFirstBinding
import com.example.ooushfitness.dto.request.LoginRequest
import com.example.ooushfitness.dto.response.LoginResponse
import com.example.ooushfitness.http.TestService
import com.example.ooushfitness.http.retrofit.RetrofitBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var testService: TestService

    private var _binding: FragmentFirstBinding? = null
    private var retrofitBuilder : RetrofitBuilder = RetrofitBuilder()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        testService = retrofitBuilder.getService(TestService::class.java) as TestService

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val loginRequest = LoginRequest();
            loginRequest.setUserName(binding.editTextTextEmailAddress.text.toString())
            loginRequest.setPassword(binding.editTextTextPassword.text.toString())

            testService.getLogin(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse = response.body()?.getData()
                    if (loginResponse != null && loginResponse.isSuccess()) {
                        binding.editTextTextEmailAddress.setText(loginResponse.getFirstName())
                        binding.editTextTextPassword.setText(loginResponse.getLocation())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.message?.let { it1 -> Log.e("error", it1) }
                }
            });
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}