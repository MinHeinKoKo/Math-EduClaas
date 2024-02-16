package com.example.math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseLevel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level)

        val level0 = findViewById<Button>(R.id.ChooseOperator)
        level0.setOnClickListener {
            val intent = Intent(this, ChooseOperator::class.java)
            startActivity(intent);
        }

        val level1 = findViewById<Button>(R.id.ChooseOperatorL1)
        level1.setOnClickListener {
            val intent = Intent(this, ChooseOperatorL1::class.java)
            startActivity(intent);
        }

        val level2 = findViewById<Button>(R.id.ChooseOperatorL2)
        level2.setOnClickListener {
            val intent = Intent(this, ChooseOperatorL2::class.java)
            startActivity(intent);
        }
    }
}