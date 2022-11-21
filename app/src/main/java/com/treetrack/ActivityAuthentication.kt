package com.treetrack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.treetrack.databinding.ActivityAuthenticationBinding

class ActivityAuthentication : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)
    }

    override fun onStart() {
        super.onStart()
        Log.e("7777", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("7777", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.e("7777", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("7777", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("7777", "onDestroy")
    }
}