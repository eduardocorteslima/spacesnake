package com.ideatech.anew

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.ideatech.anew.GameUtils.distanceBetween
import com.ideatech.anew.GameUtils.generateBorderObstacles
import com.ideatech.anew.GameUtils.generateRandomPosition
import kotlinx.coroutines.delay
import kotlin.math.pow

@Composable
fun SnakeScreenGame(
    soundManager: GameSoundManager,
    gameStateManager: GameStateManager,
    particleSystem: ParticleSystem,
    onExitGame: () -> Unit
) {

    var configData = ConfigData( segmentSize = 20)
    var snakeState by remember { mutableStateOf(Snake()) }
    var food by remember { mutableStateOf(configData.food) }
    var score by remember { mutableIntStateOf(0) }
    var isGameOver by remember { mutableStateOf(false) }
    var pulsatingScale by remember { mutableFloatStateOf(1f) }
    var snakeColor = Color.Green
    val baseSpeed = 200L
    var p by remember { mutableStateOf(true) }
    var hard = false
    val context = LocalContext.current
    var size by remember { mutableStateOf(Size.Zero) }


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .focusable()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .wrapContentHeight()
            ) {
                ScoreBoard(score, gameStateManager.recordScore, p)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .background(Color.Black)
                    .fillMaxHeight(.5f)
            ) {
                if (isGameOver) {
                    GameUtils.triggerVibration(context)
                    Text(
                        text = "Game Over\n\n☺\n\nScore: $score",
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    SnakeGame(
                        snake = snakeState,
                        modifier = Modifier.fillMaxSize().onGloballyPositioned { layoutCoordinates ->
                            size = layoutCoordinates.size.toSize()
                        },
                        pulsatingScale = pulsatingScale,
                        particleSystem = particleSystem,
                        configData = configData
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                ControlButtons(
                    onToggleMusic = { p = soundManager.toggleBackgroundMusic() },
                    onRestart = {
                        snakeState = Snake()
                        score = 0
                        isGameOver = false
                        configData.refreshObstacles(hard, size)
                        configData.obstacles
                    },
                    onClose = { onExitGame() },
                    onDifficultMode = {
                        hard = !hard
                    })
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        DirectionControls(onDirectionChange = { direction ->
                            if (!isGameOver) {
                                snakeState = snakeState.changeDirection(direction)
                            }
                        })
                    }
                }
            }

        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            if (!isGameOver) {
                snakeState = snakeState.move(
                    size.width,
                    size.height,
                    configData.segmentSize
                )

                val head = snakeState.positions.first()
                isGameOver = gameOverValidation(head, configData, size, isGameOver, snakeState)

                if (food.specialFood != null && distanceBetween(
                        head, food.specialFood!!.position
                    ) < configData.segmentSize
                ) {
                    GameSoundManager.playEatSound()
                    snakeState = snakeState.grow(5).setColor(Color.Blue)
                    score += 5
                    particleSystem.emit(head)
                    snakeColor = Color.Blue
                    pulsatingScale = 1.5f
                    delay(100)
                    pulsatingScale = 1f
                    snakeColor = Color.Green

                    food.specialFood = null
                }

                if (distanceBetween(food.position, head) < configData.segmentSize) {
                    GameSoundManager.playEatSound()
                    snakeState = snakeState.grow().setColor(Color.Blue)
                    food = configData.refreshFood(size)
                    score += 1
                    particleSystem.emit(head)
                    snakeColor = Color.Blue
                    pulsatingScale = 1.5f
                    delay(100)

                    pulsatingScale = 1f
                    snakeColor = Color.Green
                } else {
                    pulsatingScale = 1f
                }

                val currentSpeed = (baseSpeed * 0.9.pow(score / 5)).toLong()
                delay(currentSpeed)

                food.updateSpecialFood(
                    generateRandomPosition(
                        size,
                        configData.obstacles,
                        configData.segmentSize
                    )
                )
            } else {
                gameStateManager.saveHighScore(score)
                delay(300)
            }
        }
    }
}

private fun gameOverValidation(
    head: Offset,
    configData: ConfigData,
    size: Size,
    isGameOver: Boolean,
    snakeState: Snake
): Boolean {
    var isGameOver1 = isGameOver
    if (head.x < 0
        || head.x + configData.segmentSize >= size.width
        || head.y < 0
        || head.y >= size.height - configData.segmentSize
    ) {
        isGameOver1 = true
    }

    if (snakeState.positions.drop(1).contains(head)) {
        isGameOver1 = true
    }

    if (configData.obstacles.any {
            distanceBetween(
                head, it.position
            ) < configData.segmentSize
        }) {
        isGameOver1 = true
    }
    return isGameOver1
}