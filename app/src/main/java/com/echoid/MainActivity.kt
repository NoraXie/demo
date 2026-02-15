package com.echoid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.echoid.ui.screens.*
import com.echoid.ui.theme.EchoIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EchoIDTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "opening") {
        composable("opening") {
            OpeningScreen()
            
            // 2秒后自动跳转到登录页
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(2000)
                navController.navigate("login") {
                    // 清除返回栈，防止点击返回键又回到开屏页
                    popUpTo("opening") { inclusive = true }
                }
            }
        }
        composable("login") {
            LoginScreen()
        }
        composable("verification") {
            VerificationScreen()
        }
        composable("home") {
            HomeScreen()
        }
        composable("verify_fail") {
            VerifyFailScreen()
        }
    }
}
