package com.example.flocknest.data.repositories

import com.example.flocknest.data.models.LoginRequest
import com.example.flocknest.data.models.User
import com.example.flocknest.data.RetrofitHelper.createService
import com.example.flocknest.data.RetrofitInstance

class UserRepository {
    private val userAuthService = RetrofitInstance.authService

    suspend fun login(username: String, password: String): Result<User> {
        return userAuthService.login(LoginRequest(username, password))
    }
}