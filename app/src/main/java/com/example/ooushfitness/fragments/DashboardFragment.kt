package com.example.ooushfitness.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ooushfitness.R
import com.example.ooushfitness.databinding.FragmentDashboardBinding
import com.example.ooushfitness.dto.response.LogoutResponse
import com.example.ooushfitness.http.retrofit.RetrofitBuilder
import com.example.ooushfitness.http.service.AuthService
import com.example.ooushfitness.storage.SessionUtils
import com.example.ooushfitness.storage.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the dashboard in the navigation.
 */
class DashboardFragment : Fragment() {

    private lateinit var authService: AuthService
    private lateinit var model: SharedViewModel

    private var _binding: FragmentDashboardBinding? = null
    private var retrofitBuilder : RetrofitBuilder = RetrofitBuilder()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true);
        authService = retrofitBuilder.getService(AuthService::class.java, context) as AuthService
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            processLogout()
        }
        return true
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
                model.sendMessage("")
                SessionUtils.clearSession(activity)
                findNavController().navigate(R.id.action_DashboardFragment_to_LoginFragment)
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })
    }
}