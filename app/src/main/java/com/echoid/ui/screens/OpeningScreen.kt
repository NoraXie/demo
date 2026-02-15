package com.echoid.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.echoid.ui.theme.Primary
import com.echoid.ui.theme.PrimaryLight

@Composable
fun OpeningScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Decorative background elements
        Box(
            modifier = Modifier
                .size(256.dp)
                .offset(x = 100.dp, y = (-100).dp)
                .background(Primary.copy(alpha = 0.05f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(256.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-100).dp, y = 100.dp)
                .background(Primary.copy(alpha = 0.05f), CircleShape)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Section
            Box(
                modifier = Modifier
                    .background(Primary.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "Logo",
                    tint = Primary,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(
                    text = "ECHO",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "ID",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Primary
                )
            }

            Text(
                text = "Seguridad en un toque",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(120.dp))

            // Loading Spinner
            val infiniteTransition = rememberInfiniteTransition()
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ),
                label = "rotation"
            )

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(3.dp, Primary.copy(alpha = 0.1f), CircleShape)
                    .padding(3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(rotation)
                        .border(3.dp, Primary, CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // WhatsApp Indicator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Primary.copy(alpha = 0.05f), CircleShape)
                    .border(1.dp, Primary.copy(alpha = 0.1f), CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Simplified WhatsApp Icon placeholder
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Primary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "WHATSAPP AUTH FIRST",
                    style = MaterialTheme.typography.labelSmall,
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        // Footer
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = "ENCRYPTED END-TO-END",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
        }
    }
}
