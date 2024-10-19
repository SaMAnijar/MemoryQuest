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
    private var countDownTimer: CountDownTimer? = null  // Certifique-se de que seja nullável

    private var level = 1  // Controla o nível do jogo
    private var currentScore = 0  // Variável para armazenar a pontuação acumulada
    private val maxLevel = 6  // Definimos o nível 6 como o último nível

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recebe o nível do intent (caso tenha vindo de outra atividade)
        level = intent.getIntExtra("level", 1)

        gridLayout = findViewById(R.id.gridLayout)
        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)

        // Inicializa o jogo com base no nível atual
        startNewGame(level)
    }

    private fun startNewGame(level: Int) {
        // Parar o timer anterior, caso exista
        countDownTimer?.cancel()

        // Definir as imagens do jogo com base no nível atual
        val cardImages = getCardImagesForLevel(level)

        // Inicializa o jogo com o número adequado de imagens
        game = Game(cardImages)
        game.initializeGame()

        // Atualiza a pontuação acumulada no jogo (mantém a pontuação entre os níveis)
        game.score = currentScore

        // Inicializa o BoardView para desenhar as cartas
        boardView = BoardView(this, game)
        boardView.createBoardView(gridLayout)

        // Atualiza a pontuação inicial no layout
        tvScore.text = "Pontuação: ${game.score}"

        // Define o callback para atualizar a pontuação quando um par é acertado
        boardView.onScoreUpdated = { newScore ->
            currentScore = newScore  // Atualiza a pontuação acumulada
            tvScore.text = "Pontuação: $currentScore"
            checkGameCompletion()  // Verifica se o jogo foi concluído
        }

        // Inicia o timer para o novo nível
        startTimer()
    }

    private fun getCardImagesForLevel(level: Int): List<Int> {
        // Lista completa de imagens disponíveis
        val allImages = listOf(
            R.drawable.ic_card1, R.drawable.ic_card2, R.drawable.ic_card3,
            R.drawable.ic_card4, R.drawable.ic_card5, R.drawable.ic_card6,
            R.drawable.ic_card7
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
        countDownTimer?.start()  // Inicia o timer
    }

    private fun checkGameCompletion() {
        // Verifica se o jogo está completo (todos os pares encontrados)
        if (game.isComplete()) {
            if (level == maxLevel) {
                // Se estiver no último nível, exibe a tela de vitória
                showVictoryScreen()
            } else {
                level++  // Aumenta o nível

                // Reinicia o jogo para o próximo nível e mantém a pontuação
                startNewGame(level)
            }
        }
    }

    private fun showVictoryScreen() {
        val intent = Intent(this, WinActivity::class.java)
        intent.putExtra("score", game.score)  // Envia a pontuação para a tela de vitória
        startActivity(intent)
        finish()  // Fecha a MainActivity
    }

    private fun showLoseScreen() {
        val intent = Intent(this, LoseActivity::class.java)
        intent.putExtra("score", game.score)  // Envia a pontuação para a tela de perder
        startActivity(intent)

        // Resetar a pontuação ao perder
        currentScore = 0

        finish()  // Fecha a MainActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()  // Cancela o timer ao destruir a atividade
    }
}

