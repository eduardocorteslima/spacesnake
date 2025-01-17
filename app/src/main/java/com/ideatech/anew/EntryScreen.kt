package com.ideatech.anew
// EntryScreen.kt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp


@Composable
fun EntryScreen(onStartGame: () -> Unit, onExitApp: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.snake_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Snake Game",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = FontFamily(Font(R.font.press_start_2p)),
                    fontSize = 40.sp,
                    shadow = Shadow(color = Color.Black, offset = Offset(2f, 2f), blurRadius = 4f)
                ),
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier.padding(top = 32.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            Column {
                Button(
                    onClick = onStartGame,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "    Play    ", color = Color.Black)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onExitApp,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(18.dp),
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(text = "Exit", color = Color.Black)
                }
            }
        }
    }
}


