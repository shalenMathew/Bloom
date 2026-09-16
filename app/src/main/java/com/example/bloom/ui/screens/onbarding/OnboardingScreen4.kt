package com.example.bloom.ui.screens.onbarding

import android.app.TimePickerDialog
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

private val BloomBackground = Color(0xFFFCFAF8)
private val BloomPrimary = Color(0xFFB35A52)
private val BloomProgressInactive = Color(0xFFF1DDDA)
private val BloomText = Color(0xFF302A28)
private val BloomSecondaryText = Color(0xFF8D7D78)
private val BloomBorder = Color(0xFFEBD4D0)

@Composable
fun OnboardingScreen4(
    onBack: () -> Unit,
    onEnterBloom: (String) -> Unit,
    onSetLater: () -> Unit,
    serifFont: FontFamily = FontFamily.Default
) {
    val context = LocalContext.current
    var selectedTime by remember { mutableStateOf("08:00 AM") }
    var selectedPeriod by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    fun showTimePicker() {
        val calendar = Calendar.getInstance()

        TimePickerDialog(
            context,
            { _, hour, minute ->
                val period = if (hour >= 12) "PM" else "AM"
                val displayHour = when {
                    hour == 0 -> 12
                    hour > 12 -> hour - 12
                    else -> hour
                }
                selectedTime = String.format(
                    "%02d:%02d %s",
                    displayHour,
                    minute,
                    period
                )
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        ).show()
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
                repeat(4) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(5.dp)
                            .background(
                                BloomPrimary,
                                RoundedCornerShape(50)
                            )
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(34.dp))

            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = null,
                tint = BloomPrimary,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "When should we remind you?",
                color = BloomText,
                fontFamily = serifFont,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We'll send one daily nudge to log how you're doing.",
                color = BloomSecondaryText,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Pick a time of day",
                color = BloomSecondaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            ReminderOption(
                title = "Morning",
                subtitle = "7:30 AM \u2014 start the day on track",
                selected = selectedPeriod == "Morning",
                onClick = {
                    selectedPeriod = "Morning"
                    selectedTime = "07:30 AM"
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            ReminderOption(
                title = "Afternoon",
                subtitle = "1:00 PM \u2014 a midday check-in",
                selected = selectedPeriod == "Afternoon",
                onClick = {
                    selectedPeriod = "Afternoon"
                    selectedTime = "01:00 PM"
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            ReminderOption(
                title = "Evening",
                subtitle = "8:00 PM \u2014 wind down and reflect",
                selected = selectedPeriod == "Evening",
                onClick = {
                    selectedPeriod = "Evening"
                    selectedTime = "08:00 PM"
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Or set a custom time",
                color = BloomSecondaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .border(
                        width = 1.dp,
                        color = BloomBorder,
                        shape = RoundedCornerShape(13.dp)
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(13.dp)
                    )
                    .clickable {
                        selectedPeriod = null
                        showTimePicker()
                    }
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedTime,
                    color = BloomText,
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Select time",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Enter Bloom",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp)
                .background(
                    BloomPrimary,
                    RoundedCornerShape(15.dp)
                )
                .clickable {
                    onEnterBloom(selectedTime)
                }
                .padding(top = 15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "I'll set this up later",
            color = BloomSecondaryText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onSetLater)
        )

        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
private fun ReminderOption(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(63.dp)
            .border(
                width = 1.dp,
                color = BloomBorder,
                shape = RoundedCornerShape(13.dp)
            )
            .background(
                Color.White,
                RoundedCornerShape(13.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .border(
                    width = 2.dp,
                    color = if (selected) BloomPrimary else Color(0xFFE9BCB6),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(BloomPrimary, CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.width(13.dp))

        Column {
            Text(
                text = title,
                color = BloomText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                color = BloomSecondaryText,
                fontSize = 12.sp
            )
        }
    }
}
