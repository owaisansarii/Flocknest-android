package com.example.flocknest.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flocknest.data.models.User
import com.example.flocknest.data.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val repository = UserRepository()

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = repository.login(username, password)
                val user = result.getOrNull()
                if (user != null) {
                    println("User: $user")
                    _loginResult.postValue(Result.success(user))
                } else {

                    _loginResult.postValue(Result.failure(Exception("Invalid username or password")))
                }
            } catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
            }
        }
    }
}