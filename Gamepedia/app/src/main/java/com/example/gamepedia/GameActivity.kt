package com.example.gamepedia

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        val jsonString = application.assets.open("question.json").bufferedReader().use { it.readText() }

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            val question = jsonObject.getString("question")
            val option1 = jsonObject.getString("option1")
            val option2 = jsonObject.getString("option2")
            val option3 = jsonObject.getString("option3")
            val option4 = jsonObject.getString("option4")
            val answer = jsonObject.getInt("answer")


        }

    }
}