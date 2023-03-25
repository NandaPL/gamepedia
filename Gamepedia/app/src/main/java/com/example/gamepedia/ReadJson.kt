package com.example.gamepedia

import org.json.JSONArray
import org.json.JSONObject

object ReadJson {
    fun getListOfQuestions(arrayJson: JSONArray): ArrayList<JSONObject>{
       val questionsList = ArrayList<JSONObject>()

        for (i in 0 until arrayJson.length()) {
            questionsList.add(arrayJson.getJSONObject(i))
        }
        return questionsList

    }
}