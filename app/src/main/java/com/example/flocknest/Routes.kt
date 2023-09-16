package com.example.flocknest

sealed class Routes (val route: String){
    object Login: Routes("Login")
    object Signup: Routes("Signup")
    object ForgotPassword: Routes("ForgotPassword")
}