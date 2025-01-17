package com.ideatech.anew

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class Particle(
    var position: Offset, var velocity: Offset, var life: Float, var color: Color
)