package com.example.bloom.ui.screens.onbarding


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloom.R
import com.example.bloom.ui.theme.Fraunces

private val BloomBackground = Color(0xFFFCFAF8)
private val BloomPrimary = Color(0xFFB35A52)
private val BloomProgressInactive = Color(0xFFF1DDDA)
private val BloomText = Color(0xFF302A28)
private val BloomSecondaryText = Color(0xFF8D7D78)

@Composable
fun OnboardingScreen1(
    onGetStarted: () -> Unit,
    serifFont: FontFamily = Fraunces
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BloomBackground)
            .padding(horizontal = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(34.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(4) { index ->
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(5.dp)
                        .background(
                            color = if (index == 0) {
                                BloomPrimary
                            } else {
                                BloomProgressInactive
                            },
                            shape = RoundedCornerShape(50)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Bloom",
            modifier = Modifier.size(94.dp)
        )

        Spacer(modifier = Modifier.height(38.dp))

        Text(
            text = "Welcome to Bloom",
            color = BloomText,
            fontFamily = serifFont,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "A simple daily companion for managing\nPCOS – track your cycle, meals,\nmovement, water, relaxation and sleep.",
            color = BloomSecondaryText,
            fontSize = 15.sp,
            lineHeight = 23.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onGetStarted,
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BloomPrimary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Get started",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(26.dp))
    }
}