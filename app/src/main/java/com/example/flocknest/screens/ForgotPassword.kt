package com.example.flocknest.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flocknest.component.CustomTopAppBar

@Composable
fun ForgotPassword(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        ScaffoldWithTopBarForgotPassword(navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBarForgotPassword(navController: NavController){
    Scaffold (
        topBar = { CustomTopAppBar(navController= navController, title = "Signup", true) },
        content = {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(text = "ForgotPassword", fontSize = 30.sp, color = Color.Black)
            }
        }
    )
}