/*package com.example.memoryquest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.memoryquest.R.id.btnPlayAgain

class WinActivity : AppCompatActivity() {

    private lateinit var tvFinalScore: TextView
    private lateinit var btnPlayAgain: Button
    private lateinit var btnExit: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        tvFinalScore = findViewById(R.id.tvFinalScore)
        btnPlayAgain = findViewById(R.id.btnPlayAgain)
        btnExit = findViewById(R.id.btnExit)

        // Recebe a pontuação da MainActivity
        val score = intent.getIntExtra("score", 0)
        tvFinalScore.text = "Parabéns! Sua pontuação: $score"

        // Configura o botão para jogar novamente
        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Fecha a WinActivity
        }

        // Configura o botão para sair
        btnExit.setOnClickListener {
            finishAffinity()  // Fecha todas as atividades e encerra o app
        }
    }
}*/

package com.example.memoryquest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WinActivity : AppCompatActivity() {

    private lateinit var tvFinalScore: TextView
    private lateinit var btnPlayAgain: Button
    private lateinit var btnExit: Button
    private var level: Int = 1  // Nível atual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        tvFinalScore = findViewById(R.id.tvFinalScore)
        btnPlayAgain = findViewById(R.id.btnJogarNovamente)
        btnExit = findViewById(R.id.btnSair)

        // Recebe a pontuação e o nível da MainActivity
        val score = intent.getIntExtra("score", 0)
        level = intent.getIntExtra("level", 1)

        tvFinalScore.text = "Parabéns! Sua pontuação: $score"

        // Jogar novamente com o próximo nível
        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("level", level)  // Passa o nível para a MainActivity
            startActivity(intent)
            finish()
        }

        // Sair do jogo
        btnExit.setOnClickListener {
            finishAffinity()  // Fecha todas as atividades
        }
    }
}


