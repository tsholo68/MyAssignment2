package com.example.lifehackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity: AppCompatActivity() {
    var index = 0
    var score = 0
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
    val questions = arrayOf(
        "US and UK crochet terms are exactly the same",
        "You can use a pencil to crochet if you lose your hook",
        "A Magic Ring is the best way to avoid a hole in the center of hats",
        "putting a paperclip on your sticth helps you count rows easily",
        "You can safely wash any crochet project in boiling water to shrink it",
        "Acylic yarn will melt if you iron it on high heat")

    val answers = arrayOf(false, true, false, true, false)
    val explanations = arrayOf(
        "Myth: A US is a single and a UK is a double.",
        "Myth: You need a hook's notch to grab the yarn.  ",
        "Hack: It allows you to pull the center tight .",
        "Hack: it's a great biginner friendly sticth maker .",
        "Myth: Many synthetic yarns will kill or melt and the wool will turn into a solid mess e.g acrylic.",
        "Hack: it's essentially plastic and kill the fibers."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)

        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        loadQuestion()

        hackButton.setOnClickListener { checkAnswer(true) }
        mythButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            index++

            if (index < questions.size) {
                loadQuestion()
                feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    fun loadQuestion() {
        questionText.text = questions[index]
    }

    fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            feedbackText.text = "Correct! 🎉\n${explanations[index]}"
            score++
        } else {
            feedbackText.text = "Wrong! ❌\n${explanations[index]}"
        }
    }
}
