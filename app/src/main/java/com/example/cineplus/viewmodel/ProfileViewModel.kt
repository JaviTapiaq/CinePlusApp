package com.example.cineplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.data.remote.dto.MeResponse
import com.example.cineplus.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class ProfileViewModel(private val repo: AuthRepository) : ViewModel() {

    private val _user = MutableStateFlow<MeResponse?>(null)
    val user: StateFlow<MeResponse?> = _user

    fun loadUser() {
        viewModelScope.launch {
            _user.value = repo.getMyUser()
        }
    }

    fun logout() {
        viewModelScope.launch {
            repo.logout()
        }
    }
}




