package com.ideatech.anew

import androidx.compose.ui.geometry.Offset

data class Food(
    var position: Offset, var isSpecial: Boolean = false
){
    var specialFood: Food? = null
    private var specialFoodTimer = 0L
    private val specialFoodDuration = 5000L

    fun updateSpecialFood(position: Offset) {
        if (specialFood != null && System.currentTimeMillis() - specialFoodTimer > specialFoodDuration) {
            specialFood = null
        }

        if (specialFood == null && (0..1000).random() < 10) { // 1%
            specialFood = Food(position, true)
            specialFoodTimer = System.currentTimeMillis()
        }
    }
}
