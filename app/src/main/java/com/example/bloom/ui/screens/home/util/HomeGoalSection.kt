package com.example.bloom.ui.screens.home.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.SentimentSatisfiedAlt
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BloomText = Color(0xFF302A28)
private val BloomSecondaryText = Color(0xFF8D7D78)
private val BloomPrimary = Color(0xFFB35A52)
private val BloomPinkCard = Color(0xFFFFF2F0)

data class GoalProgressItem(
    val title: String,
    val progress: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val iconColor: Color
)

@Composable
fun HomeGoalSection(
    serifFont: FontFamily
) {
    val goals = listOf(
        GoalProgressItem(
            "Meals",
            "0/3 meals",
            Icons.Outlined.Restaurant,
            Color(0xFFB35A52)
        ),
        GoalProgressItem(
            "Movement",
            "0/30 min",
            Icons.Outlined.DirectionsRun,
            Color(0xFF719C82)
        ),
        GoalProgressItem(
            "Water",
            "0/8 glasses",
            Icons.Outlined.WaterDrop,
            Color(0xFF4D8CB5)
        ),
        GoalProgressItem(
            "Relaxation",
            "0/15 min",
            Icons.Outlined.SelfImprovement,
            Color(0xFF8B7BB3)
        ),
        GoalProgressItem(
            "Sleep",
            "0/8 hrs",
            Icons.Outlined.Bedtime,
            Color(0xFF5D5C82)
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HomeActionCard(
            title = "Cycle",
            subtitle = "Log your last period date",
            icon = Icons.Outlined.WaterDrop,
            iconColor = BloomPrimary,
            backgroundColor = BloomPinkCard
        )

        Spacer(modifier = Modifier.height(10.dp))

        HomeActionCard(
            title = "Symptom check-in",
            subtitle = "Bloating, skin, mood, sleep quality",
            icon = Icons.Outlined.SentimentSatisfiedAlt,
            iconColor = BloomPrimary,
            backgroundColor = BloomPinkCard
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Goal Progress",
            color = BloomText,
            fontFamily = serifFont,
            fontSize = 17.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        goals.forEachIndexed { index, goal ->
            GoalProgressCard(goal)

            if (index < goals.lastIndex) {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun HomeActionCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 70.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(19.dp),
                spotColor = iconColor.copy(alpha = 0.2f)
            )
            .background(
                backgroundColor,
                RoundedCornerShape(19.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .size(42.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(14.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = BloomText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                color = BloomSecondaryText,
                fontSize = 11.sp
            )
        }

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = BloomSecondaryText,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Composable
private fun GoalProgressCard(
    goal: GoalProgressItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 68.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(19.dp),
                spotColor = Color.Black.copy(alpha = 0.05f)
            )
            .background(
                Color.White,
                RoundedCornerShape(19.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier.size(46.dp),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Canvas(
                modifier = Modifier.size(46.dp)
            ) {
                drawCircle(
                    color = goal.iconColor,
                    radius = size.minDimension / 2
                )
                drawCircle(
                    color = Color.White,
                    radius = size.minDimension / 2 - 5.dp.toPx()
                )
            }

            Icon(
                imageVector = goal.icon,
                contentDescription = null,
                tint = goal.iconColor,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = goal.title,
                color = BloomText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = goal.progress,
                color = BloomSecondaryText,
                fontSize = 12.sp
            )
        }

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = BloomSecondaryText,
            modifier = Modifier.size(22.dp)
        )
    }
}