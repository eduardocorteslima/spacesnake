package com.ideatech.anew

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ideatech.anew.ui.theme.NewTheme
class MainActivity : ComponentActivity() {
    private val particleSystem = ParticleSystem()
    private lateinit var gameSoundManager: GameSoundManager
    private lateinit var gameStateManager: GameStateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Configura a splash screen
        super.onCreate(savedInstanceState)
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        // O gerenciamento de sons e estados do jogo
        gameSoundManager = GameSoundManager.initializeSounds(this)
        gameStateManager = GameStateManager(getPreferences(MODE_PRIVATE))

        // Aqui você pode definir a duração da splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Iniciar a tela de entrada após 2 segundos
            setContent {
                NewTheme {
                    EntryScreen(
                        onStartGame = { startGame() },
                        onExitApp = { finish() }
                    )
                }
            }
        }, 100) // 2000 ms = 2 segundos
    }

    private fun startGame() {
        setContent {
            NewTheme {
                SnakeScreenGame(
                    gameSoundManager,
                    gameStateManager,
                    particleSystem,
                    onExitGame = { startEntryScreen() }
                )
            }
        }
    }

    private fun startEntryScreen() {
        setContent {
            NewTheme {
                EntryScreen(
                    onStartGame = { startGame() },
                    onExitApp = { finish() }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        GameSoundManager.release()
    }
}