package com.example.bloom.ui.screens.home.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloom.R

private val BloomCardBackground = Color(0xFFFFF8F7)
private val BloomPrimary = Color(0xFF8F403A)
private val BloomText = Color(0xFF302A28)
private val BloomSecondaryText = Color(0xFF8D7D78)
private val BloomProgressBackground = Color(0xFFF0DCD9)

@Composable
fun TodaysGoalsCard(
    serifFont: FontFamily = FontFamily.Default
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 166.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(34.dp),
                spotColor = Color(0xFFB35A52).copy(alpha = 0.2f),
                ambientColor = Color(0xFFB35A52).copy(alpha = 0.2f)
            )
            .background(
                color = BloomCardBackground,
                shape = RoundedCornerShape(34.dp)
            )
            .padding(horizontal = 16.dp)
    ) {
        // Gradient background behind flower
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth(0.5f)
                .padding(end = 8.dp)
                .fillMaxHeight()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFF3DFDC).copy(alpha = 0.5f),
                            Color.Transparent
                        )
                    )
                )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Today's Goals",
                    color = BloomPrimary,
                    fontFamily = serifFont,
                    fontSize = 21.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(1.dp))

                Text(
                    text = "Saturday, June 27",
                    color = BloomSecondaryText,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Let's get blooming",
                    color = BloomText,
                    fontFamily = serifFont,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(1.dp))

                Text(
                    text = "0 of 5 complete",
                    color = BloomSecondaryText,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(4.dp)
                        .background(
                            BloomProgressBackground,
                            RoundedCornerShape(50.dp)
                        )
                )
            }

            Box(
                modifier = Modifier.size(130.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.bloom_bg),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )

                Text(
                    text = "Start here",
                    color = BloomPrimary,
                    fontFamily = serifFont,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
