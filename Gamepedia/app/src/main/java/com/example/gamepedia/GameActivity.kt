package com.example.gamepedia

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.gamepedia.databinding.ActivityGameBinding
import org.json.JSONArray
import org.json.JSONObject

class GameActivity : AppCompatActivity(), View.OnClickListener {
    var mPoints: Int = 0
    private var mOptionSelected: Int = 0
    private lateinit var binding: ActivityGameBinding
    private var i: Int = 0
    private var mListOfQuestions: ArrayList<JSONObject>? = null
    private var mCurrentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        /**
         * Creating instance and receiving view reference for calling/instantiating view components
         */
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /**
         * Reading a JSON file
         */
        val jsonString = application.assets.open("question.json").bufferedReader().use { it.readText() }

        val jsonArray = JSONArray(jsonString)

        mListOfQuestions = ReadJson.getListOfQuestions(jsonArray)

        // Exiba as informações do objeto na posição atual
        displayQuestion(mListOfQuestions!![mOptionSelected])

        binding.tvQuestion.setOnClickListener(this)
        binding.tvOption1.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tv_option_1 -> {
                mOptionSelected = 1
                verifyAnswer(mOptionSelected, mListOfQuestions!![mCurrentPosition])
            }
            R.id.tv_option_2 -> {
                mOptionSelected = 2
                verifyAnswer(mOptionSelected, mListOfQuestions!![mCurrentPosition])
            }
            R.id.tv_option_3 -> {
                mOptionSelected = 3
                verifyAnswer(mOptionSelected, mListOfQuestions!![mCurrentPosition])
            }
            R.id.tv_option_4 -> {
                mOptionSelected = 4
                verifyAnswer(mOptionSelected, mListOfQuestions!![mCurrentPosition])
            }
        }
    }

    // Crie um método para atualizar os TextViews com as informações do objeto
    private fun displayQuestion(question: JSONObject) {
        binding.tvQuestion.text = question.getString("question")
        binding.tvOption1.text = question.getString("option1")
        binding.tvOption2.text = question.getString("option2")
        binding.tvOption3.text = question.getString("option3")
        binding.tvOption4.text = question.getString("option4")
    }

    private fun verifyAnswer(optionSelected: Int, question: JSONObject){
        mCurrentPosition++
        val answer = question.getInt("answer")

        if (optionSelected != answer){
            showAnswer(optionSelected, R.drawable.incorrect_answer)
            displayQuestion(mListOfQuestions!![mCurrentPosition])
        }
        else{
            mPoints += 10
            showAnswer(answer, R.drawable.correct_answer)
            displayQuestion(mListOfQuestions!![mCurrentPosition])
        }
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, binding.tvOption1)
        options.add(1, binding.tvOption2)
        options.add(2, binding.tvOption3)
        options.add(3, binding.tvOption4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@GameActivity,
                R.color.white
            )
        }
    }

    private fun showAnswer(optionSelected: Int, colorOption: Int){
        when(optionSelected){
            1 -> {
                binding.tvOption1.background = ContextCompat.getDrawable(this@GameActivity, colorOption)
            }
            2 -> {
                binding.tvOption2.background = ContextCompat.getDrawable(this@GameActivity, colorOption)
            }
            3 -> {
                binding.tvOption3.background = ContextCompat.getDrawable(this@GameActivity, colorOption)
            }
            4 -> {
                binding.tvOption4.background = ContextCompat.getDrawable(this@GameActivity, colorOption)
            }
        }
    }
}