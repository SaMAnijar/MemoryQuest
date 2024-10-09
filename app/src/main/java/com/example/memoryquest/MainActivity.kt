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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)

        // Inicializando o jogo com as imagens das cartas
        val cardImages = listOf(
            R.drawable.ic_card1, R.drawable.ic_card2
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
        startActivity(intent)
        finish()  // Fecha a MainActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()  // Cancela o timer ao destruir a atividade
    }
}