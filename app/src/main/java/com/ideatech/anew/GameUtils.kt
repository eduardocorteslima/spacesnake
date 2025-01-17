package com.ideatech.anew

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

object GameUtils {

    fun distanceBetween(p1: Offset, p2: Offset): Float {
        return sqrt((p1.x - p2.x).pow(2) + (p1.y - p2.y).pow(2))
    }

    fun triggerVibration(context: Context) {
        val vibrator = context.getSystemService(Vibrator::class.java) as Vibrator

        vibrator.let {
            if (it.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                }
            }
        }
    }

        fun generateObstacles(
            size: Size,
            segmentSize: Int,
            numberOfObstacles: Int
        ): List<Obstacle> {
            return List(numberOfObstacles) {
                Obstacle(
                    generateRandomPosition(
                        size,
                        emptyList(),
                        segmentSize
                    )
                )
            }
        }

        fun generateRandomPosition(
            size: Size,
            obstacles: List<Obstacle>,
            segmentSize: Int
        ): Offset {
            var position: Offset
            do {
                position = Offset(
                    (Random.nextInt(
                        0,
                        (size.width / segmentSize).toInt()
                    ) * segmentSize).toFloat(),
                    (Random.nextInt(
                        0,
                        (size.height / segmentSize).toInt()
                    ) * segmentSize).toFloat()
                )
            } while (obstacles.any {
                    distanceBetween(
                        it.position,
                        position
                    ) < segmentSize
                })

            return position
        }

        fun generateBorderObstacles(
            size: Size, segmentSize: Int
        ): List<Obstacle> {
            val obstacles = mutableListOf<Obstacle>()

            for (x in 0 until size.width.toInt() step segmentSize) {
                obstacles.add(Obstacle(Offset(x.toFloat(), 0f)))
            }

            // Bottom
            for (x in 0 until size.width.toInt() step segmentSize) {
                obstacles.add(Obstacle(Offset(x.toFloat(), size.height - segmentSize)))
            }

            // Left
            for (y in 0 until size.height.toInt() - segmentSize  step segmentSize) {
                obstacles.add(Obstacle(Offset(0f, y.toFloat())))
            }

            // Right
            for (y in 0 until size.height.toInt() - segmentSize step segmentSize) {
                obstacles.add(Obstacle(Offset(size.width - segmentSize + 3, y.toFloat())))
            }

            return obstacles
        }
    }
