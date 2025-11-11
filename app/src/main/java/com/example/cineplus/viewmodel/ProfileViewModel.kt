package com.example.cineplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.repository.UserRepository
import com.example.cineplus.data.entities.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser

    fun loadUser(username: String) {
        viewModelScope.launch {
            _currentUser.value = repository.getUserByUsername(username)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearSession()
            _currentUser.value = null
        }
    }
}



