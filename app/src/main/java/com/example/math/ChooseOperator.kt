package com.example.math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseOperator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_operator)

        val level0Add = findViewById<Button>(R.id.Level0Add)
        level0Add.setOnClickListener {
            val calInt = Intent(this, Level0::class.java)
            calInt.putExtra("cals","+")
            startActivity(calInt)
        }

        val level0Substract = findViewById<Button>(R.id.Level0Subtract)
        level0Substract.setOnClickListener {
            val calInt = Intent(this, Level0::class.java)
            calInt.putExtra("cals","-")
            startActivity(calInt)
        }

        val level0Multiply= findViewById<Button>(R.id.Level0Multiply)
        level0Multiply.setOnClickListener {
            val calInt = Intent (this,Level0::class.java)

            calInt.putExtra("cals","*")
            startActivity(calInt)
        }

        val level0Divide= findViewById<Button>(R.id.Level0Divide)
        level0Divide.setOnClickListener {
            val calInt = Intent (this,Level0::class.java)

            calInt.putExtra("cals","/")
            startActivity(calInt)
        }
    }
}