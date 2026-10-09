package com.learning.auth_ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


data class User(
    val id: Int=0,
    val userEmail: String="",
    val userPassword: String=""
)


class AuthViewModel : ViewModel() {

    private var _user: MutableStateFlow<User> = MutableStateFlow(User())

    val user = _user.asStateFlow()


    fun updateUser(user: User){
        _user.value = user
    }

}