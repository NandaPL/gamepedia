package com.example.gamepedia

class Question(string: String, string1: Any, string2: Any, string3: Any, string4: Any, int: Any) : ArrayList<QuestionItem>()

data class QuestionItem(
    val answer: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val question: String
)