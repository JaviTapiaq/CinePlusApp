package com.example.cineplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError

    /**
     * Intenta iniciar sesión con las credenciales dadas
     */
    fun login(username: String, password: String) {
        viewModelScope.launch {
            val success = authRepository.login(username, password)
            if (success) {
                _isLoggedIn.value = true
                _loginError.value = null
            } else {
                _isLoggedIn.value = false
                _loginError.value = "Usuario o contraseña incorrectos"
            }
        }
    }

    /**
     * Registra un nuevo usuario
     */
    fun register(username: String, password: String) {
        viewModelScope.launch {
            authRepository.register(username, password)
        }
    }

    /**
     * Cierra sesión y resetea estados
     */
    fun logout() {
        _isLoggedIn.value = false
        _loginError.value = null
    }
}










