package com.example.flocknest.data.services

import com.example.flocknest.data.models.LoginRequest
import com.example.flocknest.data.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAuthService{
    @POST("users/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Result<User>
}