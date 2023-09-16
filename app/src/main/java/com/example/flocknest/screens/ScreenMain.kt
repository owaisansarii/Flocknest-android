package com.example.flocknest.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flocknest.Routes

@Composable
fun ScreenMain (){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route){
        composable(Routes.Login.route){
            LoginPage(navController=navController)
        }
        composable(Routes.Signup.route){
            Signup(navController=navController)
        }
        composable(Routes.ForgotPassword.route){
            ForgotPassword(navController=navController)
        }
    }
}