package com.ideatech.anew

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DirectionControls(onDirectionChange: (Snake.Direction) -> Unit) {
    val buttonColor = Color.Yellow.copy(alpha = 0.2f, red = 1f)
    val buttonModifier = Modifier.size(60.dp)
    val buttonSize = 60.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(buttonSize)
                .shadow(8.dp, shape = CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.White, buttonColor), radius = 120f
                    ), shape = CircleShape
                ), contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { onDirectionChange(Snake.Direction.Up) },
                shape = CircleShape,
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
            ) {
                Text("↑", fontSize = 24.sp, color = Color.Black)
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .size(buttonSize)
                    .shadow(8.dp, shape = CircleShape)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color.White, buttonColor), radius = 120f
                        ), shape = CircleShape
                    ), contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { onDirectionChange(Snake.Direction.Left) },
                    shape = CircleShape,
                    modifier = buttonModifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                ) {
                    Text("←", fontSize = 24.sp, color = Color.Black)
                }
            }
            Text("♣", color = Color.Black)
            Spacer(modifier = Modifier.size(60.dp))
            Text("♠", color = Color.Black)
            Box(
                modifier = Modifier
                    .size(buttonSize)
                    .shadow(8.dp, shape = CircleShape)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color.White, buttonColor), radius = 120f
                        ), shape = CircleShape
                    ), contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { onDirectionChange(Snake.Direction.Right) },
                    shape = CircleShape,
                    modifier = buttonModifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                ) {
                    Text("→", fontSize = 24.sp, color = Color.Black)
                }
            }
        }

        Box(
            modifier = Modifier
                .size(buttonSize)
                .shadow(8.dp, shape = CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.White, buttonColor), radius = 120f
                    ), shape = CircleShape
                ), contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { onDirectionChange(Snake.Direction.Down) },
                shape = CircleShape,
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
            ) {
                Text("↓", fontSize = 24.sp, color = Color.Black)
            }
        }
    }
}