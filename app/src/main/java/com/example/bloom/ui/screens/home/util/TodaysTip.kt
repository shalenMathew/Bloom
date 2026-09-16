package com.example.bloom.ui.screens.home.util


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.Check
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

private val TipBackground = Color(0xFFF8E9CE)
private val TipTitle = Color(0xFF95601D)
private val TipText = Color(0xFF302A28)
private val TipIconBackground = Color(0xFFE8E7F0)
private val TipIconColor = Color(0xFF65688C)
private val RemindBackground = Color(0xFFE9DEC9)
private val DoneBackground = Color(0xFF75A184)

@Composable
fun TodaysTip(
    serifFont: FontFamily
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Today's tip",
            color = TipText,
            fontFamily = serifFont,
            fontSize = 19.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(25.dp),
                    spotColor = TipTitle.copy(alpha = 0.15f)
                )
                .background(
                    TipBackground,
                    RoundedCornerShape(25.dp)
                )
                .padding(
                    start = 20.dp,
                    top = 18.dp,
                    end = 14.dp,
                    bottom = 18.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            TipIconBackground,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Bedtime,
                        contentDescription = null,
                        tint = TipIconColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.size(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "LOG LAST NIGHT'S SLEEP",
                        color = TipTitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "You haven't logged sleep yet. Consistent,close to 8-hr nights can help keep your hormones more balanced — avoiding phones and screens before bed makes it easier to fall asleep.",
                        color = TipText,
                        fontSize = 15.sp,
                        lineHeight = 23.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .heightIn(min = 40.dp)
                                .background(
                                    RemindBackground,
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Remind me",
                                color = TipText,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                        }

                        Box(
                            modifier = Modifier
                                .heightIn(min = 40.dp)
                                .background(
                                    DoneBackground,
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )

                                Text(
                                    text = "Mark done",
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}