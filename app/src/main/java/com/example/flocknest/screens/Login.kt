package com.example.flocknest.screens

import android.graphics.drawable.Icon
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flocknest.Routes
import com.example.flocknest.data.repositories.UserRepository
import com.example.flocknest.data.viewmodels.UserViewModel
import com.example.flocknest.ui.theme.Purple40

data class ErrorState(var type: String = "none", var message: String = "")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavHostController){
    val viewModel: UserViewModel = viewModel()
    val user = viewModel.loginResult.observeAsState()
    val username = remember {
        mutableStateOf(TextFieldValue())
    }

    val password = remember {
        mutableStateOf(TextFieldValue())
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var error by remember { mutableStateOf("") }

    fun validatePassword(password: String){
        var errorState = when{
            password.isEmpty() -> ErrorState("empty", "Password cannot be empty")
            password.length < 8 -> ErrorState("length", "Password must be at least 8 characters")
            else -> ErrorState()
        }
        val regex = Regex(pattern = "[A-Za-z0-9]+")
        val isValid = regex.matches(input = password)
        if (!isValid){
            errorState.message = "Password must only contain letters and numbers"
            errorState.type = "invalid"
        }
        error = errorState.message
    }



    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5F5))){
        ClickableText(text = AnnotatedString("Sign up here"), onClick ={navController.navigate(Routes.Signup.route)}, modifier = Modifier
            .align(
                Alignment.BottomCenter
            )
            .padding(20.dp), style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.Default, color = Purple40 ))
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Login",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),
                modifier = Modifier.padding(bottom = 8.dp)
                )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = username.value,
                onValueChange = {username.value =it},
                label = { Text(text = "Username")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                visualTransformation= if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                value = password.value,
                onValueChange = {
                    password.value =it
                    validatePassword(it.text)

                                },
                label = { Text(text = "Password")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                supportingText = {
                    if (error.isNotEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                isError = error.isNotEmpty(),
                trailingIcon = {
                    val image = if (passwordVisible){
                        Icons.Filled.Visibility
                    }else{
                        Icons.Filled.VisibilityOff
                    }
                    val description = if (passwordVisible) "Hide Password" else "Show Password"

                    IconButton(onClick = {passwordVisible=!passwordVisible}) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp,0.dp,40.dp,0.dp)) {
                Button(
                    shape = RoundedCornerShape(50.dp),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Login")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(text = AnnotatedString("Forgot Password?"), onClick = {navController.navigate(Routes.ForgotPassword.route)}, style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.Default))
//            show user data
            user.value?.let {
                Text(text = it.toString())
            }
        }
    }
}