package com.example.gamepedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gamepedia.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val mContext = this@ResultActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val points = intent.getIntExtra("PONTUATION", 0)
        val numberQuestions = intent.getIntExtra("NUMBER_OF_QUESTIONS", 0)

        val correctQuestions = points / 10
        binding.tvCorrectQuestions.text = "$correctQuestions/$numberQuestions"
        binding.tvPoints.text = "$points pontos"

        val mediumCorrectQuestions = numberQuestions / 2

        if(numberQuestions >= mediumCorrectQuestions){
            binding.tvMessage.text = getString(R.string.win_message)
        }
        else {
            binding.tvMessage.text = getString(R.string.lost_message)
        }

        binding.buttonBackMenu.setOnClickListener{
            startActivity(Intent(mContext, MainActivity::class.java))
        }

    }
}