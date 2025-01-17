package com.ideatech.anew

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class Snake(
    val positions: List<Offset> = List(6) { Offset(100f, 100f) },
    val direction: Direction = Direction.Right,
    val color: Color = Color(0xFF006400)
) {
    enum class Direction {
        Up, Down, Left, Right
    }

    fun setColor(newColor: Color): Snake {
        return this.copy(color = newColor)
    }

    fun move(screenWidth: Float, screenHeight: Float, segmentSize: Int): Snake {
        val head = positions.first()
        val newHead = when (direction) {
            Direction.Up -> head.copy(y = (head.y - segmentSize).coerceAtLeast(0f))
            Direction.Down -> head.copy(y = (head.y + segmentSize).coerceAtMost(screenHeight - segmentSize))
            Direction.Left -> head.copy(x = (head.x - segmentSize).coerceAtLeast(0f))
            Direction.Right -> head.copy(x = (head.x + segmentSize).coerceAtMost(screenWidth - segmentSize.toFloat()))
        }

        val newPositions = listOf(newHead) + positions.dropLast(1)

        return this.copy(positions = newPositions)
    }

    fun grow(count: Int = 1): Snake {
        val newTailSegments = List(count) { positions.last() }
        return this.copy(positions = positions + newTailSegments)
    }

    fun changeDirection(newDirection: Direction): Snake {
        return if ((direction == Direction.Up && newDirection == Direction.Down) ||
            (direction == Direction.Down && newDirection == Direction.Up) ||
            (direction == Direction.Left && newDirection == Direction.Right) ||
            (direction == Direction.Right && newDirection == Direction.Left)
        ) {
            this
        } else {
            this.copy(direction = newDirection)
        }
    }
}