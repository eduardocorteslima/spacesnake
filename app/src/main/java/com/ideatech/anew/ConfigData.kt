package com.ideatech.anew

import androidx.compose.ui.geometry.Size
import com.ideatech.anew.GameUtils.generateBorderObstacles
import com.ideatech.anew.GameUtils.generateObstacles
import com.ideatech.anew.GameUtils.generateRandomPosition

data class ConfigData(
    val segmentSize: Int
) {
    private var  size: Size = Size(500f,530f)
    var obstacles: List<Obstacle> = refreshObstacles()
    var food: Food = refreshFood()

    fun refreshFood(newSize:Size? = null): Food {
        if (newSize != null) {
            size = newSize
        }
        food = Food(generateRandomPosition(
                size,
                obstacles,
                segmentSize
            )
        )
        return food
    }

    fun refreshObstacles(modeHardOn: Boolean = false, newSize:Size? = null): List<Obstacle>{
        if (newSize != null) {
            size = newSize
        }
        obstacles = generateObstacles(
            size = size,
            segmentSize = segmentSize,
            numberOfObstacles = getNumberOfObstacles(modeHardOn)
        )
        return obstacles
    }
    private fun getNumberOfObstacles(modeHardOn: Boolean): Int{
        if (modeHardOn) return 50
        return 10
    }
}