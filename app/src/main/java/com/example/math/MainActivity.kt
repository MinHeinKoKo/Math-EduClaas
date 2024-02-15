package com.example.math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chooseLevel = findViewById<Button>(R.id.ChooseLevel)
        chooseLevel.setOnClickListener {
            val Intent = Intent( this,ChooseLevel::class.java)
            startActivity(Intent)
        }
    }
}