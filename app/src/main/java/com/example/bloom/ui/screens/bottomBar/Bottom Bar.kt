package com.example.bloom.ui.screens.bottomBar


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloom.R

private val BloomBackground = Color(0xFFFCFAF8)
private val BloomPrimary = Color(0xFFB35A52)
private val BloomInactive = Color(0xFF8D7D78)

@Composable
fun BloomBottomBar(
    selectedItem: Int = 0,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        "Dashboard",
        "Insights",
        "Learn",
        "Settings"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .background(BloomBackground)
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, label ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onItemSelected(index)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (index == 0) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = label,
                        tint = if (selectedItem == index) {
                            BloomPrimary
                        } else {
                            BloomInactive
                        },
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    val icon = when (index) {
                        1 -> R.drawable.insights
                        2 -> R.drawable.learn
                        else -> R.drawable.settings
                    }

                    Image(
                        painter = painterResource(icon),
                        contentDescription = label,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Text(
                    text = label,
                    color = if (selectedItem == index) {
                        BloomPrimary
                    } else {
                        BloomInactive
                    },
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}