package com.example.math

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class Level1 : AppCompatActivity() {

    var timeTextView : TextView? =null
    var questionTextView : TextView? =null
    var alertTextView : TextView? =null
    var scoreTextView : TextView? =null
    var finalScoreTextView1 : TextView? =null
    var finalScoreTextView2 : TextView? =null
    var progressBar : ProgressBar? = null
    var button0 : Button? =null
    var button1 : Button? =null
    var button2 : Button? =null
    var button3 : Button? =null

    var countDownTimer : CountDownTimer? =null
    var random : Random =Random
    var a = 0
    var b = 0
    var indexOfCorrectAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 1
    var cals = ""

    var applauseSound : MediaPlayer? = null
    var correctSound : MediaPlayer? = null
    var inCorrectSound : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        val calInt = intent.getStringExtra("cals")
        cals = calInt !!

//        timeTextView = findViewById(R.id.timeTextView)
        questionTextView = findViewById(R.id.questionTextView)
        alertTextView = findViewById(R.id.alertTextView)
        scoreTextView = findViewById(R.id.scoreTextView)
        progressBar = findViewById(R.id.progressBar)

        button0 =findViewById(R.id.button0)
        button1 =findViewById(R.id.button1)
        button2 =findViewById(R.id.button2)
        button3 =findViewById(R.id.button3)

        start()

    }


    fun NextQuestion(cal:String){
        if (totalQuestions <= 10) {
            a = random.nextInt(6, 12)
            b = random.nextInt(1, 5)
            questionTextView!!.text = "$a $cal $b"
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
                button0!!.text = "${answers[0]}"
                button1!!.text = "${answers[1]}"
                button2!!.text = "${answers[2]}"
                button3!!.text = "${answers[3]}"

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun optionSelect(view:View?) {
        totalQuestions in 1 until 10
        if(totalQuestions <= 10){

            if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
                points++
                alertTextView!!.text = "🍾 Good Job"
                correctSound = MediaPlayer.create(this, R.raw.correct)
                correctSound?.start()
            }

            else {
                alertTextView!!.text = "😞😞Wrong"
                inCorrectSound = MediaPlayer.create(this, R.raw.incorrect)
                inCorrectSound?.start()
            }
            scoreTextView!!.text = "$points/$totalQuestions"

            if (totalQuestions <10){
                NextQuestion(cals)
                totalQuestions++
                countDownTimer!!.start()
            }else{
                countDownTimer!!.cancel()
                openDialog()
            }
        } else{
            openDialog()
        }
    }


    private fun start() {
        if (totalQuestions <= 10){
            NextQuestion(cals)
            countDownTimer = object : CountDownTimer(20000,1000){
                override fun onTick(millisUntilFinished: Long) {
//                    timeTextView!!.text = (millisUntilFinished / 1000).toString() + "s"
                    val progress = (millisUntilFinished / 1000).toInt()
                    progressBar!!.progress = progress
                }

                override fun onFinish() {
//                    timeTextView!!.text = "Times' Up!"
                    openDialog()
                }

            }.start()
        }
    }


    private fun openDialog() {
        val inflate =LayoutInflater.from(this)
        val winDialog = inflate.inflate(R.layout.activity_total_score,null)
        finalScoreTextView1 =winDialog.findViewById(R.id.FinalScoreTextView1)
        finalScoreTextView2 = winDialog.findViewById(R.id.FinalScoreTextView2)
        val chooseLevel = winDialog.findViewById<Button>(R.id.ChooseLevel)
        chooseLevel.setOnClickListener {
            val intentChooseLevel = Intent (this,ChooseLevel::class.java)
            startActivity(intentChooseLevel)
        }
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(winDialog)
        finalScoreTextView1!!.text = "Marks     : $points"
        finalScoreTextView2!!.text = "Questions : $totalQuestions"
        val showDialog = dialog.create()
        showDialog.show()
        applauseSound = MediaPlayer.create(this, R.raw.applause)
        applauseSound?.start()
    }

}