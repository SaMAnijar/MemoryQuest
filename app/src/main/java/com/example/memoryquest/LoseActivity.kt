package com.example.memoryquest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lose)

        // Recupera a pontuação passada pela MainActivity
        val score = intent.getIntExtra("score", 0)

        // Exibe a pontuação no TextView
        val tvFinalScore = findViewById<TextView>(R.id.tvFinalScore)
        tvFinalScore.text = "Sua pontuação: $score"

        // Botão para tentar novamente
        val btnTentar = findViewById<Button>(R.id.btnTentar)
        btnTentar.setOnClickListener {
            finish()  // Voltar para a MainActivity
        }

        // Botão para sair do jogo
        val btnSair = findViewById<Button>(R.id.btnSair)
        btnSair.setOnClickListener {
            finishAffinity()  // Fechar o aplicativo
        }
    }
}
