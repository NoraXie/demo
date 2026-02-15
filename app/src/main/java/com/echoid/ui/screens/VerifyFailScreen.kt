package com.echoid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.echoid.ui.theme.ErrorColor
import com.echoid.ui.theme.Primary
import com.echoid.ui.theme.Slate900

@Composable
fun VerifyFailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Toolbar
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { /* Back */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp, 4.dp)
                    .background(Color.LightGray, CircleShape)
                    .align(Alignment.TopCenter)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Icon Header (Waiting/Warning state)
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Primary.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MarkChatUnread,
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Esperando confirmación...",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Validando tu identidad vía WhatsApp",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // OTP with Error
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FailOtpBox("4")
            FailOtpBox("7")
            FailOtpBox("2")
            FailOtpBox("9", isError = true)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Error Message
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Error, null, tint = ErrorColor, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Código incorrecto o expirado",
                color = ErrorColor,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Retry Button
        Button(
            onClick = { /* Retry */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Slate900),
            shape = RoundedCornerShape(28.dp)
        ) {
            Icon(Icons.Default.Refresh, null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Reintentar Verificación",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resend
        TextButton(onClick = { /* Resend */ }) {
            Text(
                "No recibí el código",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                style = MaterialTheme.typography.labelLarge
            )
        }

        // WhatsApp Link
        TextButton(onClick = { /* Open WhatsApp */ }) {
            Text(
                "Volver a abrir WhatsApp",
                color = Primary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun FailOtpBox(value: String, isError: Boolean = false) {
    Surface(
        modifier = Modifier
            .size(64.dp, 80.dp)
            .border(
                2.dp,
                if (isError) ErrorColor else Color.LightGray.copy(alpha = 0.2f),
                RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(value, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = if (isError) ErrorColor else MaterialTheme.colorScheme.onBackground)
        }
    }
}
