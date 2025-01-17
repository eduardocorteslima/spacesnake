package com.ideatech.anew

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ControlButtons(
    onToggleMusic: () -> Unit,
    onRestart: () -> Unit,
    onClose: () -> Unit,
    onDifficultMode: () -> Unit
) {
    var isMusicOn by remember { mutableStateOf(true) }
    var isDifficultModeOn by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Button(
            onClick = onRestart, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) {
            Text(
                "↻",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
        }
        Button(onClick = {
            isMusicOn = !isMusicOn
            onToggleMusic()
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)) {
            Text(
                "♪",
                fontSize = 24.sp,
                fontWeight = if (isMusicOn) FontWeight.Bold else FontWeight.Normal,
                color = if (!isMusicOn) Color.Gray else Color.DarkGray
            )
        }
        Button(
            onClick = {
                isDifficultModeOn = !isDifficultModeOn
                onDifficultMode()
            }, colors = ButtonDefaults.buttonColors(
                containerColor = if (isDifficultModeOn) Color.Yellow else Color.Yellow
            )
        ) {
            Text(
                text = if (isDifficultModeOn) "\uD83D\uDD25" else "\uD83C\uDF31",
                fontWeight = if (isDifficultModeOn) FontWeight.Bold else FontWeight.Normal,
                color = if (!isDifficultModeOn) Color.Black else Color.White
            )
        }
        Button(
            onClick = onClose, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) { Text("❌") }
    }
}