package com.example.flocknest.data

import com.example.flocknest.data.services.UserAuthService

object RetrofitInstance{
    val authService: UserAuthService by lazy {
        RetrofitHelper.createService(UserAuthService::class.java)
    }
}