/*package com.example.memoryquest

import BoardView
import Game
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView

    private lateinit var game: Game
    private lateinit var boardView: BoardView
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)

        // Inicializando o jogo com as imagens das cartas
        val cardImages = listOf(
            R.drawable.ic_card1, R.drawable.ic_card2, R.drawable.ic_card3,
            R.drawable.ic_card4, R.drawable.ic_card5, R.drawable.ic_card6,
        )
        game = Game(cardImages)
        game.initializeGame()

        // Inicializando o BoardView para desenhar as cartas
        boardView = BoardView(this, game)
        boardView.createBoardView(gridLayout)

        // Atualiza a pontuação inicial no layout
        tvScore.text = "Pontuação: ${game.score}"

        // Define o callback para atualizar a pontuação quando um par é acertado
        boardView.onScoreUpdated = { newScore ->
            tvScore.text = "Pontuação: $newScore"
        }

        // Inicia o timer
        startTimer()
    }

    private fun updateScore(score: Int) {
        tvScore.text = "Pontuação: $score"
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {  // 60 segundos, intervalo de 1 segundo
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = "Tempo: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                showLoseScreen()  // Exibe a tela de perder quando o tempo acabar
            }
        }
        countDownTimer.start()
    }

    private fun showLoseScreen() {
        val intent = Intent(this, LoseActivity::class.java)
        intent.putExtra("score", game.score)  // Passa a pontuação para a LoseActivity
        startActivity(intent)
        finish()  // Fecha a MainActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()  // Cancela o timer ao destruir a atividade
    }
}*/


package com.example.memoryquest

import BoardView
import Game
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView

    private lateinit var game: Game
    private lateinit var boardView: BoardView
    private lateinit var countDownTimer: CountDownTimer

    private var level = 1  // Controla o nível do jogo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)

        // Inicializa o jogo com base no nível atual
        startNewGame(level)
    }

    private fun startNewGame(level: Int) {
        // Definir as imagens do jogo com base no nível atual
        val cardImages = getCardImagesForLevel(level)

        // Inicializa o jogo com o número adequado de imagens
        game = Game(cardImages)
        game.initializeGame()

        // Inicializa o BoardView para desenhar as cartas
        boardView = BoardView(this, game)
        boardView.createBoardView(gridLayout)

        // Atualiza a pontuação inicial no layout
        tvScore.text = "Pontuação: ${game.score}"

        // Define o callback para atualizar a pontuação quando um par é acertado
        boardView.onScoreUpdated = { newScore ->
            tvScore.text = "Pontuação: $newScore"
        }

        // Inicia o timer
        startTimer()
    }

    private fun getCardImagesForLevel(level: Int): List<Int> {
        // Lista completa de imagens disponíveis
        val allImages = listOf(
            R.drawable.ic_card1, R.drawable.ic_card2, R.drawable.ic_card3,
            R.drawable.ic_card4, R.drawable.ic_card5, R.drawable.ic_card6,
            R.drawable.ic_card7, R.drawable.ic_card8, R.drawable.ic_card9,
            R.drawable.ic_card10, R.drawable.ic_card11, R.drawable.ic_card12
        )

        // O número de pares de cartas aumenta conforme o nível
        val numPairs = level + 1  // Exemplo: nível 1 = 2 pares, nível 2 = 3 pares, etc.

        // Retorna a quantidade adequada de imagens do conjunto total
        return allImages.take(numPairs)
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {  // 60 segundos, intervalo de 1 segundo
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = "Tempo: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                showLoseScreen()  // Exibe a tela de perder quando o tempo acabar
            }
        }
        countDownTimer.start()
    }

    private fun showWinScreen() {
        // Aumenta o nível e reinicia o jogo
        level++
        val intent = Intent(this, WinActivity::class.java)
        intent.putExtra("score", game.score)
        intent.putExtra("level", level)  // Passa o nível atualizado para a WinActivity
        startActivity(intent)
        finish()  // Fecha a MainActivity
    }

    private fun showLoseScreen() {
        val intent = Intent(this, LoseActivity::class.java)
        intent.putExtra("score", game.score)
        startActivity(intent)
        finish()  // Fecha a MainActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()  // Cancela o timer ao destruir a atividade
    }
}
