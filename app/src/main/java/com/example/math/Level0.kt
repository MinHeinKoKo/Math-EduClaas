package com.example.math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class Level0 : AppCompatActivity() {

    var questionTextViewL0 : TextView ?= null
    var alertTextViewL0 : TextView ?= null
    var scoreTextViewL0 : TextView ?= null
    var finalScoreTextView : TextView? =null
    var button5 : Button? =null
    var button6 : Button? =null
    var button7 : Button? =null
    var button8 : Button? =null

    var random: Random = Random
    var a = 0;
    var b = 0;
    var indexOfCorrectAnswer = 0;
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 1
    var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level0)

        val calInt = intent.getStringExtra("cals")
        cals = calInt !!

        button5 =findViewById(R.id.button0)
        button6 =findViewById(R.id.button1)
        button7 =findViewById(R.id.button2)
        button8 =findViewById(R.id.button3)
    }

    fun NextQuestion(cal:String) {
        a = random.nextInt(6 , 12)
        b = random.nextInt(1,5)
        questionTextViewL0!!.text  = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)

        answers.clear()

        for (i in 0..3){
            if (indexOfCorrectAnswer == i) {
                when(cals){
                    "+" -> {
                        answers.add(a + b)
                    }
                    "-" -> {
                        answers.add(a - b)
                    }
                    "*" -> {
                        answers.add(a * b)
                    }
                    "/" -> {
                        try {
                            answers.add(a / b)
                        }

                        catch (e:Exception){
                            e.printStackTrace()
                        }
                    }
                }
            } else{
                var wrongAnswer = random.nextInt(15)
                try {
                    while (
                        wrongAnswer == a+b ||
                        wrongAnswer == a-b
                        || wrongAnswer == a*b
                        || wrongAnswer == a/b
                    ) {
                        wrongAnswer = random.nextInt(15)
                    }
                    answers.add(wrongAnswer)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

        fun optionSelect (view: View?) {
            totalQuestions in 1 until  10

            if(totalQuestions<=10){

                if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
                    points++
                    alertTextViewL0!!.text = "Correct"
                }

                else {
                    alertTextViewL0!!.text = "Wrong"
                }
                scoreTextViewL0!!.text = "$points/$totalQuestions"
                NextQuestion(cals)
                totalQuestions++

            }

            else{
                System.out.println("Error")
            }
        }
        //private fun openDialog() {
//            val inflate =LayoutInflater.from(this)
//            val winDialog = inflate.inflate(R.layout.activity_total_score,null)
//            finalScoreTextView =winDialog.findViewById(R.id.FinalScoreTextView)
//            val chooseLevel = winDialog.findViewById<Button>(R.id.ChooseLevel)
//            chooseLevel.setOnClickListener {
//                val intentChooseLevel = Intent (this,ChooseLevel::class.java)
//                startActivity(intentChooseLevel)
//            }
//            val dialog = AlertDialog.Builder(this)
//            dialog.setCancelable(false)
//            dialog.setView(winDialog)
//            finalScoreTextView!!.text = "$points/$totalQuestions"
//            val showDialog = dialog.create()
//            showDialog.show()
        //}
    }
}