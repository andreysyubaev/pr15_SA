package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import layout.Question

class MainActivity2 : AppCompatActivity() {

    lateinit var trueButton: Button
    lateinit var falseButton: Button
    lateinit var nextButton: Button
    lateinit var previousButton: Button
    lateinit var questionTextView: TextView

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast}
        else{
            R.string.incorrect_toast}

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }

    val questionBank = listOf(
        Question.Question(R.string.question_text1, true),
        Question.Question(R.string.question_text2, true),
        Question.Question(R.string.question_text3, true),
        Question.Question(R.string.question_text4, false),
        Question.Question(R.string.question_text5, true)
    )
    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)

        questionTextView = findViewById(R.id.question_text_view)

        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

        trueButton.setOnClickListener {
            checkAnswer(true)
            /*val truetop = Toast.makeText(this,R.string.correct_toast, Toast.LENGTH_SHORT)
            truetop.setGravity(Gravity.TOP, 0, 0)
            truetop.show()*/

        }

        falseButton.setOnClickListener {
            checkAnswer(false)
            /*val falsetop = Toast.makeText(this,R.string.incorrect_toast, Toast.LENGTH_SHORT)
                falsetop.setGravity(Gravity.TOP, 0, 0)
                    falsetop.show()*/
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        previousButton.setOnClickListener {
            if ((currentIndex - 1) < 0){
                currentIndex = 0
                updateQuestion()
            }
            else {
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()
            }
        }
        updateQuestion()

    }
}

