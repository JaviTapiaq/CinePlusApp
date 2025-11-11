package com.example.cineplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.data.DatabaseProvider
import com.example.cineplus.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(context: Context) : ViewModel() {

    private val authRepo = AuthRepository(DatabaseProvider.getDatabase(context).userDao())

    fun register(username: String, password: String) {
        viewModelScope.launch {
            authRepo.register(username, password)
        }
    }

    fun login(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val success = authRepo.login(username, password)
            if (success) onSuccess() else onError("Usuario o contraseña incorrectos")
        }
    }
}

