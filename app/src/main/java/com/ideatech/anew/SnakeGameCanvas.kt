package com.ideatech.anew

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.ideatech.anew.GameUtils.generateBorderObstacles

@Composable
fun SnakeGame(
    snake: Snake,
    modifier: Modifier = Modifier,
    pulsatingScale: Float,
    particleSystem: ParticleSystem,
    configData: ConfigData,
) {
    Canvas(modifier = modifier) {
        drawGrid(size = size, configData.segmentSize)

        val segmentSizePulsar = configData.segmentSize * pulsatingScale

        snake.positions.forEachIndexed { index, position ->
            val color = if (index == 0) snake.color else Color.Green
            drawRect(
                color = color,
                topLeft = position,
                size = Size(segmentSizePulsar, segmentSizePulsar)
            )
        }

        configData.obstacles.forEach { obstacle ->
            drawRect(
                color = Color.Gray,
                topLeft = obstacle.position,
                size = Size(configData.segmentSize.toFloat(), configData.segmentSize.toFloat())
            )
        }

        drawHeart(position = configData.food!!.position, segmentSize = segmentSizePulsar)

        configData.food!!.specialFood?.let { food ->
            drawClub(position = food.position, segmentSize = segmentSizePulsar)
        }

        particleSystem.update()
        particleSystem.draw(this)
    }
}