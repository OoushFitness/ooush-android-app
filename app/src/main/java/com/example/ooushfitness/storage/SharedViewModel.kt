package com.example.ooushfitness.storage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

public class SharedViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    fun sendMessage(text: String) {
        message.value = text
    }
}