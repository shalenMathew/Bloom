package com.example.bloom.ui.screens.onbarding



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BloomBackground = Color(0xFFFCFAF8)
private val BloomPrimary = Color(0xFFB35A52)
private val BloomProgressInactive = Color(0xFFF1DDDA)
private val BloomText = Color(0xFF302A28)
private val BloomSecondaryText = Color(0xFF7A6B66)
private val BloomBackButton = Color(0xFFF3DFDC)

@Composable
fun OnboardingScreen2(
    onBack: () -> Unit,
    onContinue: (String) -> Unit,
    serifFont: FontFamily = FontFamily.Default
) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BloomBackground)
            .padding(horizontal = 26.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(BloomBackButton, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = BloomPrimary,
                        modifier = Modifier.size(17.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(4) { index ->
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(5.dp)
                            .background(
                                if (index < 2) BloomPrimary else BloomProgressInactive,
                                RoundedCornerShape(50)
                            )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "A little about you",
            color = BloomText,
            fontFamily = serifFont,
            fontSize = 23.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            placeholder = {
                Text(
                    text = "Your first name",
                    color = BloomSecondaryText,
                    fontSize = 14.sp
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(13.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = BloomText,
                unfocusedTextColor = BloomText,
                focusedBorderColor = BloomPrimary,
                unfocusedBorderColor = Color(0xFFEBD4D0),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = BloomPrimary
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onContinue(name) },
            enabled = name.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BloomPrimary,
                disabledContainerColor = Color(0xFFE7C0BA),
                contentColor = Color.White,
                disabledContentColor = Color.White
            )
        ) {
            Text(
                text = "Continue",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(26.dp))
    }
}