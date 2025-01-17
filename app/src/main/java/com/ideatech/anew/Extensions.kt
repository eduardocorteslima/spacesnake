package com.ideatech.anew

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

fun DrawScope.drawHeart(position: Offset, segmentSize: Float) {
    drawIntoCanvas { canvas ->
        val textPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.RED
            textSize = segmentSize * 1.5f
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
        }
        val xPos = position.x + segmentSize / 2
        val yPos = position.y + segmentSize

        canvas.nativeCanvas.drawText("♥", xPos, yPos, textPaint)
    }
}

fun DrawScope.drawClub(position: Offset, segmentSize: Float) {
    drawIntoCanvas { canvas ->
        val textPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.MAGENTA
            textSize = segmentSize * 1.5f
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
        }
        val xPos = position.x + segmentSize / 2
        val yPos = position.y + segmentSize

        canvas.nativeCanvas.drawText("♣", xPos, yPos, textPaint)
    }
}

fun DrawScope.drawGrid(size: Size, segmentSize: Int) {
    val lineColor = Color(0x4AE0E0E0)
    val lineSpacing = segmentSize.toFloat()

    for (x in 0..size.width.toInt() step lineSpacing.toInt()) {
        drawLine(
            color = lineColor,
            start = Offset(x.toFloat(), 0f),
            end = Offset(x.toFloat(), size.height)
        )
    }

    for (y in 0..size.height.toInt() step lineSpacing.toInt()) {
        drawLine(
            color = lineColor,
            start = Offset(0f, y.toFloat()),
            end = Offset(size.width, y.toFloat())
        )
    }
}