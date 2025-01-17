package com.ideatech.anew

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.random.Random

class ParticleSystem {
    private val particles = mutableListOf<Particle>()

    fun emit(position: Offset) {
        val particleCount = 10
        for (i in 0 until particleCount) {
            val velocity = Offset(
                Random.nextFloat() * 2 - 1, Random.nextFloat() * 2 - 1
            )
            val life = Random.nextFloat() * 1.5f + 0.5f
            val color =
                Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            particles.add(Particle(position, velocity, life, color))
        }
    }

    fun update() {
        particles.removeIf { particle ->
            particle.life <= 0
        }

        particles.forEach { particle ->
            particle.position = particle.position.copy(
                x = particle.position.x + particle.velocity.x * 5,
                y = particle.position.y + particle.velocity.y * 5
            )
            particle.life -= 0.05f
        }
    }

    fun draw(drawScope: DrawScope) {
        particles.forEach { particle ->
            val alpha = (particle.life / 2).coerceIn(0f, 1f)
            drawScope.drawCircle(
                color = particle.color.copy(alpha = alpha), radius = 5f,
                center = particle.position
            )
        }
    }
}