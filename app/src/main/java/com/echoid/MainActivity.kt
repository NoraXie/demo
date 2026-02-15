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
import com.echoid.sdk.EchoID

class MainActivity : ComponentActivity() {
    private var navigationTarget by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize EchoID SDK
        EchoID.init(this, "api-test-123", "EchoID-Demo")

        // Handle incoming intent (initial launch)
        handleDeepLink(intent)

        setContent {
            EchoIDTheme {
                val navController = rememberNavController()
                
                // 监听状态变化并跳转
                LaunchedEffect(navigationTarget) {
                    navigationTarget?.let { target ->
                        navController.navigate(target) {
                            popUpTo(0) // 清理栈
                        }
                        navigationTarget = null
                    }
                }

                AppNavigation(navController)
            }
        }
    }

    override fun onNewIntent(intent: android.content.Intent?) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: android.content.Intent?) {
        intent?.data?.let { uri ->
            if (uri.scheme == "echoid" && uri.host == "login") {
                val token = uri.getQueryParameter("token")
                val otp = uri.getQueryParameter("otp")
                
                if (token != null && otp != null) {
                    EchoID.verifyOTP(token, otp, object : EchoID.Callback {
                        override fun onSuccess(waId: String) {
                            navigationTarget = "home"
                        }

                        override fun onFailure(error: String) {
                            navigationTarget = "verify_fail"
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: androidx.navigation.NavHostController) {
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
