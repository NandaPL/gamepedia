package com.example.gamepedia

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.gamepedia.databinding.ActivityGameBinding
import org.json.JSONArray
import org.json.JSONObject

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private var mPoints: Int = 0
    private var mOptionSelected: Int = 0
    private lateinit var binding: ActivityGameBinding
    private var mListOfQuestions: ArrayList<JSONObject>? = null
    private var mCurrentPosition = 0
    private val mContext = this@GameActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Creating instance and receiving view reference for calling/instantiating view components
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Reading a JSON file
        val jsonString = application.assets.open("question.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)

        mListOfQuestions = ReadJson.getListOfQuestions(jsonArray)

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
                verifyAnswer(1, mListOfQuestions!![mCurrentPosition])
            }
            R.id.tv_option_2 -> {
                verifyAnswer(2, mListOfQuestions!![mCurrentPosition])
            }
            R.id.tv_option_3 -> {
                verifyAnswer(3, mListOfQuestions!![mCurrentPosition])
            }
            R.id.tv_option_4 -> {
                verifyAnswer(4, mListOfQuestions!![mCurrentPosition])
            }
        }
    }

    private fun displayQuestion(question: JSONObject) {
        binding.tvQuestion.text = question.getString("question")
        binding.tvOption1.text = question.getString("option1")
        binding.tvOption2.text = question.getString("option2")
        binding.tvOption3.text = question.getString("option3")
        binding.tvOption4.text = question.getString("option4")
    }

    // Selected Response Evaluation Function
    private fun verifyAnswer(optionSelected: Int, question: JSONObject){
        val answer = question.getInt("answer")

        if(optionSelected == answer){
            mPoints += 10
            verifyOptionSelected(answer, R.drawable.correct_answer)
        }
        else{
            verifyOptionSelected(optionSelected, R.drawable.incorrect_answer)
        }



        if (mCurrentPosition < mListOfQuestions!!.size - 1){
            mCurrentPosition++

            Handler().postDelayed({
                defaultOptionsView()
                displayQuestion(mListOfQuestions!![mCurrentPosition])
            }, 2000) // aguarda 1 segundo (1000 milissegundos) antes de exibir a próxima questão

        }
        else{
            val intent = Intent(mContext, ResultActivity::class.java)
                .putExtra("PONTUATION", mPoints)
                .putExtra("NUMBER_OF_QUESTIONS", mListOfQuestions!!.size)
            startActivity(intent)
        }
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, binding.tvOption1)
        options.add(1, binding.tvOption2)
        options.add(2, binding.tvOption3)
        options.add(3, binding.tvOption4)

        for (option in options) {
            option.setTextColor(Color.DKGRAY)
            option.background = ContextCompat.getDrawable(
                mContext,
                R.color.white
            )
        }
    }

    // Response Option Selection Capture
    private fun verifyOptionSelected(optionSelected: Int, colorOption: Int){
        when(optionSelected){
            1 -> {
                binding.tvOption1.background = ContextCompat.getDrawable(mContext, colorOption)
            }
            2 -> {
                binding.tvOption2.background = ContextCompat.getDrawable(mContext, colorOption)
            }
            3 -> {
                binding.tvOption3.background = ContextCompat.getDrawable(mContext, colorOption)
            }
            4 -> {
                binding.tvOption4.background = ContextCompat.getDrawable(mContext, colorOption)
            }
        }
    }
}
