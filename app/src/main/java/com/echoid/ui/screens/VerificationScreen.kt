package com.echoid.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MarkChatUnread
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.echoid.ui.theme.Primary

@Composable
fun VerificationScreen() {
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
            // iOS Handle simulation
            Box(
                modifier = Modifier
                    .size(48.dp, 4.dp)
                    .background(Color.LightGray, CircleShape)
                    .align(Alignment.TopCenter)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Icon Header
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

        // Instructions
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                VerifyStep("1", "Envía el mensaje en WhatsApp.")
                Spacer(modifier = Modifier.height(12.dp))
                VerifyStep("2", "Recibe tu código de 4 dígitos.")
                Spacer(modifier = Modifier.height(12.dp))
                VerifyStep("3", "Ingrésalo aquí abajo.")
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // OTP Input
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OtpBox("4")
            OtpBox("7")
            OtpBox("", isFocused = true)
            OtpBox("")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Confirm Button
        Button(
            onClick = { /* Confirm */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Confirmar código",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resend
        TextButton(onClick = { /* Resend */ }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Refresh, null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "No recibí el código",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                    style = MaterialTheme.typography.labelLarge
                )
            }
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
fun VerifyStep(number: String, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier.size(24.dp),
            color = Primary,
            shape = CircleShape
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(number, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun OtpBox(value: String, isFocused: Boolean = false) {
    Surface(
        modifier = Modifier.size(64.dp, 80.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, if (isFocused) Primary else Color.LightGray.copy(alpha = 0.2f)),
        color = if (isFocused) Color.White else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (value.isEmpty() && !isFocused) {
                Text("•", fontSize = 24.sp, color = Color.LightGray)
            } else {
                Text(value, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
