package com.example.ooushfitness.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ooushfitness.R
import com.example.ooushfitness.databinding.FragmentSignupBinding
import com.example.ooushfitness.dto.request.RegisterUserRequest
import com.example.ooushfitness.dto.response.RegisterUserResponse
import com.example.ooushfitness.http.retrofit.RetrofitBuilder
import com.example.ooushfitness.http.service.UserService
import com.example.ooushfitness.storage.SharedViewModel
import com.example.ooushfitness.utils.OoushUtils
import com.google.gson.internal.LinkedTreeMap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass as the login location in the navigation.
 */
class SignupFragment : Fragment()  {

    private lateinit var userService: UserService
    private lateinit var model: SharedViewModel

    private var _binding: FragmentSignupBinding? = null
    private var retrofitBuilder : RetrofitBuilder = RetrofitBuilder()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        userService = retrofitBuilder.getService(UserService::class.java, context) as UserService
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.INVISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        binding.buttonSignUp.setOnClickListener {
            processRegisterUser()
        }
        binding.editTextConfirmPassword.addTextChangedListener(addPasswordMatchListener())
        binding.buttonSignUp.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addPasswordMatchListener(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.editTextPassword.length() > 0) {
                    if (OoushUtils.getTextFromEditor(binding.editTextPassword) != s.toString()) {
                        binding.registerText.text = getString(R.string.passwords_dont_match_warning)
                    } else {
                        binding.buttonSignUp.isEnabled = true
                        binding.registerText.text = null
                    }
                }
            }
        }
    }

    private fun processRegisterUser() {
        binding.progressBar.visibility = View.VISIBLE
        binding.buttonSignUp.visibility = View.INVISIBLE
        userService.registerUser(createRegisterUserRequest()).enqueue(object : Callback<RegisterUserResponse> {
            override fun onResponse(call: Call<RegisterUserResponse>, response: Response<RegisterUserResponse>) {
                retrieveRegisterUserResponseData(response)
            }
            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })
    }

    private fun createRegisterUserRequest(): RegisterUserRequest {
        val registerUserRequest = RegisterUserRequest()
        registerUserRequest.setFirstName(OoushUtils.getTextFromEditor(binding.editTextEnterFirstName))
        registerUserRequest.setLastName(OoushUtils.getTextFromEditor(binding.editTextEnterLastName))
        registerUserRequest.setUserName(OoushUtils.getTextFromEditor(binding.editTextEnterUserName))
        registerUserRequest.setEmail(OoushUtils.getTextFromEditor(binding.editTextEnterEmailAddress))
        registerUserRequest.setPhoneNumber(OoushUtils.getTextFromEditor(binding.editTextPhoneNumber))
        registerUserRequest.setPassword(OoushUtils.getTextFromEditor(binding.editTextPassword))
        return registerUserRequest
    }

    private fun retrieveRegisterUserResponseData(response: Response<RegisterUserResponse>) {
        if (response.code() == 404) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.buttonSignUp.visibility = View.VISIBLE
            binding.registerText.text = getString(R.string.connection_error)
            Executors.newSingleThreadScheduledExecutor().schedule({
                binding.registerText.text = null
            }, 7, TimeUnit.SECONDS)
        } else if (response.isSuccessful) {
            binding.progressBar.visibility = View.INVISIBLE

            @Suppress("UNCHECKED_CAST")
            val responseMap: LinkedTreeMap<String, String> =
                response.body()?.getData() as LinkedTreeMap<String, String>
            responseMap["body"]?.let { model.sendMessage(it) }
            findNavController().navigate(R.id.action_SignupFragment_to_LoginFragment)
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.buttonSignUp.visibility = View.VISIBLE
            binding.registerText.text = getString(R.string.not_successful)
            Executors.newSingleThreadScheduledExecutor().schedule({
                binding.registerText.text = null
            }, 7, TimeUnit.SECONDS)
        }
    }

}