package com.example.cineplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.repository.AuthRepository

import kotlinx.coroutines.launch

class AuthViewModel(context: Context) : ViewModel() {

    private val authRepository = AuthRepository(context)

    fun register(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            authRepository.register(
                username = username,
                password = password,
                onSuccess = onSuccess,
                onError = onError
            )
        }
    }

    fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            authRepository.login(
                username = username,
                password = password,
                onSuccess = onSuccess,
                onError = onError
            )
        }
    }
}
