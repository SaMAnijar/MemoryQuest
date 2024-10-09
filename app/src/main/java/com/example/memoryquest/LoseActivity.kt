package com.example.memoryquest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lose)

        val btnTryAgain = findViewById<Button>(R.id.btnTentar)
        val btnExit = findViewById<Button>(R.id.btnSair)

        // Botão "Tentar novamente"
        btnTryAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Fecha a tela de perdedor
        }

        // Botão "Sair" leva de volta para a tela inicial
        btnExit.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()  // Fecha a tela de perdedor
        }
    }
}
