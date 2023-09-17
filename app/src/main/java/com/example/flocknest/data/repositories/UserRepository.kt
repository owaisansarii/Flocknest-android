package com.example.flocknest.data.repositories

import com.example.flocknest.data.models.LoginRequest
import com.example.flocknest.data.models.User
import com.example.flocknest.data.RetrofitHelper.createService
import com.example.flocknest.data.RetrofitInstance

class UserRepository {
    private val userAuthService = RetrofitInstance.authService

    suspend fun login(username: String, password: String): Result<User> {
        val loginRequest = LoginRequest(username, password)
        val result = userAuthService.login(loginRequest)
        if (result.isSuccess) {
            val user = result.getOrNull()
            if (user != null) {
                println("User: $user")
                return Result.success(user)
            }
        } else if (result.isFailure) {
            return Result.failure(result.exceptionOrNull()!!)
        }
        return result
    }
}