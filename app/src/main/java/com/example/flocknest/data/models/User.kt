package com.example.flocknest.data.models


data class User(
    val id: String,
    val userId: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val createdAt: String,
    val updatedAt: String,
    val token: String,
    val refreshToken: String
)