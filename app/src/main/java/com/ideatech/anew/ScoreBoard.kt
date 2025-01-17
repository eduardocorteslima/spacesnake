package com.ideatech.anew

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScoreBoard(score: Int, recordScore: Int, p: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Score: $score", fontSize = 24.sp, color = Color.White)
        Text(
            text = "\"♪\"",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (p) Color.Yellow else Color.DarkGray,
        )
        Text(text = "Record: $recordScore", fontSize = 24.sp, color = Color.White)
    }
}