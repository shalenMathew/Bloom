package com.example.bloom.ui.screens.home.util


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BloomBackground = Color(0xFFFCFAF8)
private val BloomText = Color(0xFF302A28)
private val BloomSecondaryText = Color(0xFF8D7D78)
private val BloomProfileBackground = Color(0xFFF3DFDC)
private val BloomPrimary = Color(0xFFB35A52)

@Composable
fun HomeHeader(
    name: String,
    serifFont: FontFamily = FontFamily.Default,
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BloomBackground)
            .padding(
                start = 22.dp,
                end = 16.dp,
                top = 28.dp,
                bottom = 18.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "GOOD MORNING,",
                color = BloomSecondaryText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                maxLines = 1
            )

            Text(
                text = " $name",
                color = BloomText,
                fontFamily = serifFont,
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = BloomProfileBackground,
                    shape = CircleShape
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonOutline,
                contentDescription = "Profile",
                tint = BloomPrimary,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}