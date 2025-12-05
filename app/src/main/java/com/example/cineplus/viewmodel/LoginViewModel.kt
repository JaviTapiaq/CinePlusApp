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
    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            authRepository.login(
                username = username,
                password = password,
                onSuccess = {
                    _isLoggedIn.value = true
                    _loginError.value = null
                },
                onError = {
                    _isLoggedIn.value = false
                    _loginError.value = "Usuario o contraseña incorrectos"
                }
            )
        }
    }


    /**
     * Registra un nuevo usuario
     */
    fun register(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            authRepository.register(
                username = username,
                password = password,
                onSuccess = {
                    // Puedes manejar el éxito del registro aquí si es necesario
                },
                onError = {
                    // Puedes manejar el error del registro aquí si es necesario
                }
            )
        }
    }

    /**
     * Cierra sesión y resetea estados
     */
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            _isLoggedIn.value = false
            _loginError.value = null
        }
    }
}
