package com.example.math

import android.content.Intent
import android.media.MediaPlayer
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
    var finalScoreTextView1 : TextView? =null
    var finalScoreTextView2 : TextView? =null
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

    var applauseSound : MediaPlayer? = null
    var correctSound : MediaPlayer? = null
    var inCorrectSound : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level0)

        val calInt = intent.getStringExtra("cals")
        cals = calInt !!

        questionTextViewL0 = findViewById(R.id.questionTextView)
        alertTextViewL0 = findViewById(R.id.alertTextView)
        scoreTextViewL0 = findViewById(R.id.scoreTextView)

        button5 =findViewById(R.id.button0)
        button6 =findViewById(R.id.button1)
        button7 =findViewById(R.id.button2)
        button8 =findViewById(R.id.button3)

        NextQuestion(cals)
    }

    fun NextQuestion(cal: String){
        if (totalQuestions <= 10) {

            a = random.nextInt(6, 12)
            b = random.nextInt(1, 5)
            questionTextViewL0!!.text = "$a $cal $b"
            indexOfCorrectAnswer = random.nextInt(4)

            answers.clear()

            for (i in 0..3) {
                if (indexOfCorrectAnswer == i) {
                    when (cal) {
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
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                } else {
                    var wrongAnswer = random.nextInt(15)
                    try {
                        while (
                            wrongAnswer == a + b
                            || wrongAnswer == a - b
                            || wrongAnswer == a * b
                            || wrongAnswer == a / b
                        ) {
                            wrongAnswer = random.nextInt(15)
                        }
                        answers.add(wrongAnswer)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            try {
                button5!!.text = "${answers[0]}"
                button6!!.text = "${answers[1]}"
                button7!!.text = "${answers[2]}"
                button8!!.text = "${answers[3]}"
                println(totalQuestions);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun optionSelect(view: View?) {
        if (totalQuestions <= 10){
            if (indexOfCorrectAnswer.toString() == view!!.tag.toString()){
                points ++
                alertTextViewL0!!.text = "Good Job"
                correctSound = MediaPlayer.create(this, R.raw.correct)
                correctSound?.start()
            }else{
                alertTextViewL0!!.text = "Wrong 😞😞"
                inCorrectSound = MediaPlayer.create(this, R.raw.incorrect)
                inCorrectSound?.start()
            }

            scoreTextViewL0!!.text = "$points/$totalQuestions"

            if (totalQuestions <10){
                totalQuestions++
                NextQuestion(cals)
            }else{
                openDialog()
            }

        }else{
            openDialog()
        }
    }

    private fun openDialog() {
        val inflate = LayoutInflater.from(this)
        val windDialog = inflate.inflate(R.layout.activity_total_score , null)
        finalScoreTextView1 =windDialog.findViewById(R.id.FinalScoreTextView1)
        finalScoreTextView2 = windDialog.findViewById(R.id.FinalScoreTextView2)
        val chooseLevel = windDialog.findViewById<Button>(R.id.ChooseLevel)

        chooseLevel.setOnClickListener {
            val intentChooseLevel = Intent (this,ChooseLevel::class.java)
            startActivity(intentChooseLevel)
        }
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(windDialog)
        finalScoreTextView1!!.text = "Marks     : $points"
        finalScoreTextView2!!.text = "Questions : $totalQuestions"
        val showDialog = dialog.create()
        showDialog.show()
        applauseSound = MediaPlayer.create(this, R.raw.applause)
        applauseSound?.start()
    }
}