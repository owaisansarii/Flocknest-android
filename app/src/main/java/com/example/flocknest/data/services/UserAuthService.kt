package com.example.flocknest.data.services

import com.example.flocknest.data.models.LoginRequest
import com.example.flocknest.data.models.User
import retrofit2.http.Body
import retrofit2.http.GET

interface UserAuthService{
    @GET("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Result<User>
}