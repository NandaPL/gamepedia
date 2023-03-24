package com.example.gamepedia

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.json.JSONArray

class GameActivity : AppCompatActivity() {
    var mQuestions: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val textQuestion = findViewById<TextView>(R.id.tv_question)
        val textOptionA = findViewById<TextView>(R.id.tv_option_1)
        val textOptionB = findViewById<TextView>(R.id.tv_option_2)
        val textOptionC = findViewById<TextView>(R.id.tv_option_3)
        val textOptionD = findViewById<TextView>(R.id.tv_option_4)


    }

    fun readJson(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val jsonString = application.assets.open("question.json").bufferedReader().use { it.readText() }

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val questionJSON = jsonArray.getJSONObject(i)

            val questionObject = Question(
                questionJSON.getString("question"),
                questionJSON.getString("option1"),
                questionJSON.getString("option2"),
                questionJSON.getString("option3"),
                questionJSON.getString("option4"),
                questionJSON.getInt("answer"),
            )
            questionsList.add(questionObject)
        }

        return questionsList
    }
}