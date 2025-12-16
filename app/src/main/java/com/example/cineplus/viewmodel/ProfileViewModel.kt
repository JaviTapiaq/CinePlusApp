package com.example.cineplus.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.data.remote.dto.MeResponse
import com.example.cineplus.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ProfileViewModel(private val repo: AuthRepository) : ViewModel() {

    private val _user = MutableStateFlow<MeResponse?>(null)
    val user: StateFlow<MeResponse?> = _user

    private val _profileImageUri = MutableStateFlow<Uri?>(null)
    val profileImageUri: StateFlow<Uri?> = _profileImageUri

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

    fun loadProfileImage(context: Context) {
        viewModelScope.launch {
            val file = File(context.filesDir, "profile_image.jpg")
            if (file.exists()) {
                _profileImageUri.value = Uri.fromFile(file)
            }
        }
    }

    fun saveProfileImage(context: Context, bitmap: Bitmap) {
        viewModelScope.launch {
            val file = File(context.filesDir, "profile_image.jpg")
            try {
                FileOutputStream(file).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                }
                _profileImageUri.value = Uri.fromFile(file)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
