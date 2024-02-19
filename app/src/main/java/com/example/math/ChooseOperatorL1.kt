package com.example.math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseOperatorL1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_operator_l1)

        val level1Add= findViewById<Button>(R.id.Level1Add)
        level1Add.setOnClickListener {
            val calInt = Intent (this,Level1::class.java)

            calInt.putExtra("cals","+")
            startActivity(calInt)
        }

        val level1Subtract= findViewById<Button>(R.id.Level1Subtract)
        level1Subtract.setOnClickListener {
            val calInt = Intent (this,Level1::class.java)

            calInt.putExtra("cals","-")
            startActivity(calInt)
        }

        val level1Multiply= findViewById<Button>(R.id.Level1Multiply)
        level1Multiply.setOnClickListener {
            val calInt = Intent (this,Level1::class.java)

            calInt.putExtra("cals","*")
            startActivity(calInt)
        }

        val level1Divide= findViewById<Button>(R.id.Level1Divide)
        level1Divide.setOnClickListener {
            val calInt = Intent (this,Level1::class.java)

            calInt.putExtra("cals","/")
            startActivity(calInt)
        }

    }
}