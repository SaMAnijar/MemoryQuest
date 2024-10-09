package com.example.memoryquest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Botão para iniciar o jogo
        val btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener {
            // Navegar para a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnSair = findViewById<Button>(R.id.btnSair)
        btnSair.setOnClickListener {
            finish()
        }
    }
}
