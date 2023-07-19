package com.example.ooushfitness.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ooushfitness.R
import com.example.ooushfitness.constants.OoushConstants
import com.example.ooushfitness.databinding.FragmentSecondBinding
import com.example.ooushfitness.dto.response.LoginResponse
import com.example.ooushfitness.dto.response.LogoutResponse
import com.example.ooushfitness.http.retrofit.RetrofitBuilder
import com.example.ooushfitness.http.service.AuthService
import com.example.ooushfitness.storage.SessionUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var authService: AuthService

    private var _binding: FragmentSecondBinding? = null
    private var retrofitBuilder : RetrofitBuilder = RetrofitBuilder()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true);
        authService = retrofitBuilder.getService(AuthService::class.java, context) as AuthService
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSecond.setOnClickListener {
            processLogout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun processLogout() {
        authService.logout().enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                SessionUtils.clearSession(activity)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })
    }
}