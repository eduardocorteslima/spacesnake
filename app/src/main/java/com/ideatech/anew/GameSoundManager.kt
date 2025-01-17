package com.ideatech.anew

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool

object GameSoundManager {
    private lateinit var soundPool: SoundPool
    private var eatSoundId: Int = 0
    private lateinit var mediaPlayer: MediaPlayer

    fun initializeSounds(context: Context): GameSoundManager {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        eatSoundId = soundPool.load(context, R.raw.eat_sound, 1)
        mediaPlayer = MediaPlayer.create(context, R.raw.game_loop_sound).apply {
            isLooping = true
            start()
        }
        return this
    }

    fun playEatSound() {
        soundPool.play(eatSoundId, 0.1f, 0.1f, 0, 0, 1f)
    }

    fun toggleBackgroundMusic(): Boolean {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
        return mediaPlayer.isPlaying
    }

    fun release() {
        mediaPlayer.release()
    }
}