package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {
    private lateinit var playername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val intent : Intent = getIntent();
        var playerString = intent.getStringExtra("username").toString()
       // Log.i("The passed username", playerString)
        playername = findViewById(R.id.playerNameText) // megkeresem a viewt a feluletrol
        playername.text =  playerString //feltoltom az erteket
    }

}