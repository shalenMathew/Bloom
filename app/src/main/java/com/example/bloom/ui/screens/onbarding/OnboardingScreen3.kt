package com.example.bloom.ui.screens.onbarding


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.SentimentSatisfiedAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BloomBackground = Color(0xFFFCFAF8)
private val BloomPrimary = Color(0xFFB35A52)
private val BloomProgressInactive = Color(0xFFF1DDDA)
private val BloomText = Color(0xFF302A28)
private val BloomSecondaryText = Color(0xFF8D7D78)
private val BloomCard = Color.White
private val BloomIconBackground = Color(0xFFFBEFED)
private val BloomDivider = Color(0xFFF2E3E0)

data class TrackingGoal(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun OnboardingScreen3(
    onBack: () -> Unit,
    onContinue: (List<String>) -> Unit,
    serifFont: FontFamily = FontFamily.Default
) {
    val goals = listOf(
        TrackingGoal(
            "Meals · 3 balanced meals",
            "Steadier blood sugar helps keep PCOS symptoms in check.",
            Icons.Default.Restaurant
        ),
        TrackingGoal(
            "Movement · 30 minutes",
            "Supports insulin sensitivity, a key lever in PCOS.",
            Icons.Default.DirectionsRun
        ),
        TrackingGoal(
            "Water · 8 glasses",
            "Helps with energy and bloating.",
            Icons.Default.WaterDrop
        ),
        TrackingGoal(
            "Relaxation · 15 minutes",
            "Lower stress means lower cortisol, which can worsen symptoms.",
            Icons.Default.Spa
        ),
        TrackingGoal(
            "Sleep · 8 hours",
            "Consistent sleep helps balance hormones over time.",
            Icons.Default.Bedtime
        ),
        TrackingGoal(
            "Cycle · period dates",
            "Spot your personal pattern and predict your next period.",
            Icons.Default.CalendarMonth
        ),
        TrackingGoal(
            "Symptom check-in · bloating, skin, mood, sleep quality",
            "Track how you feel day to day to notice patterns over time.",
            Icons.Default.SentimentSatisfiedAlt
        )
    )

    val enabledGoals = remember {
        mutableStateListOf<Boolean>().apply {
            repeat(goals.size) {
                add(true)
            }
        }
    }

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
                    .background(Color(0xFFF3DFDC), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
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
                                if (index < 3) BloomPrimary else BloomProgressInactive,
                                RoundedCornerShape(50)
                            )
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "✣",
                color = BloomPrimary,
                fontSize = 29.sp
            )

            Spacer(modifier = Modifier.height(13.dp))

            Text(
                text = "What would you like to track\ndaily?",
                color = BloomText,
                fontFamily = serifFont,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Pick the goals that matter to you — only these will\nshow up on your dashboard. Partial progress still\ncounts, and your flower fills in as you go.",
                color = BloomSecondaryText,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        BloomCard,
                        RoundedCornerShape(17.dp)
                    )
                    .padding(horizontal = 16.dp)
            ) {
                goals.forEachIndexed { index, goal ->
                    TrackingGoalItem(
                        goal = goal,
                        enabled = enabledGoals[index],
                        onToggle = {
                            enabledGoals[index] = !enabledGoals[index]
                        }
                    )

                    if (index < goals.lastIndex) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(BloomDivider)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Targets and what you track can be adjusted any time\nin Settings.",
                color = BloomSecondaryText,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        androidx.compose.material3.Button(
            onClick = {
                onContinue(
                    goals.filterIndexed { index, _ -> enabledGoals[index] }
                        .map { it.title }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            shape = RoundedCornerShape(15.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = BloomPrimary,
                contentColor = Color.White
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

@Composable
private fun TrackingGoalItem(
    goal: TrackingGoal,
    enabled: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(BloomIconBackground, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = goal.icon,
                contentDescription = null,
                tint = BloomPrimary,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = goal.title,
                color = BloomText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = goal.description,
                color = BloomSecondaryText,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        BloomToggle(
            checked = enabled,
            onCheckedChange = onToggle
        )
    }
}

@Composable
private fun BloomToggle(
    checked: Boolean,
    onCheckedChange: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(42.dp)
            .height(25.dp)
            .clickable { onCheckedChange() }
            .background(
                if (checked) BloomPrimary else Color(0xFFE3D8D5),
                RoundedCornerShape(50)
            )
            .border(
                width = 1.dp,
                color = if (checked) BloomPrimary else Color(0xFFD5C9C6),
                shape = RoundedCornerShape(50)
            )
            .padding(2.dp),
        contentAlignment = if (checked) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(19.dp)
                .background(Color.White, CircleShape)
        )
    }
}