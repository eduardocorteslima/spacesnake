package com.ideatech.anew

import android.content.SharedPreferences

class GameStateManager(private val sharedPreferences: SharedPreferences) {
    private val highScoreKey = "HIGH_SCORE"
    var recordScore = loadHighScore()

    fun saveHighScore(score: Int) {
        if (score > recordScore) {
            recordScore = score
            with(sharedPreferences.edit()) {
                putInt(highScoreKey, recordScore)
                apply()
            }
        }
    }

    private fun loadHighScore(): Int {
        return sharedPreferences.getInt(highScoreKey, 0)
    }
}
