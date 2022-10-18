package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate() called")
        initViewItems()
        registerListeners()
    }

    private fun registerListeners() {
        startButton.setOnClickListener {
            //handle click event
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() called.")
    }

    private fun intiViewItems() {
        startButton = findViewById(R.id.startButton)
    }

    override fun onResume() {
        super.onResume()
        print("onResume")
    }

    override fun onPause() {
        super.onPause()
        print("onPause")
    }

    override fun onStop() {
        super.onStop()
        print("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        print("onRestart")
    }


    override fun onDestroy() {
        super.onDestroy()
        print("onDestroy")
    }

}

//eletciklus metodusok(activity lifecicles)

/*Activity lifecycle has seven methods

    onCreate()
    onStart()
    onResume()
    onPause()
    onStop()
    onRestart()
    onDestroy()*/

/*feladathivjunk uj Activityt
* */