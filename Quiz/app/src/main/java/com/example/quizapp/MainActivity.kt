package com.example.quizapp

import UiQuiz.HomeFragment
import UiQuiz.QuizEndFragment
import UiQuiz.QuizStartFragment
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import java.lang.Exception

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        setContentView(R.layout.activity_main)
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.fragment_placeHolder, QuizStartFragment())
        ft.commit()
        initializeView()
        menu()
    }

    lateinit var topAppBar: MaterialToolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var homeNavView: MenuItem

    private fun initializeView() {
        topAppBar = findViewById(R.id.topAppBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
    }


    fun selectDrawerItem(menuItem: MenuItem) {

        // Create a new fragment and specify the fragment to show based on nav item clicked
        var fragment: Fragment? = null
        val fragmentClass: Class<*>
        menuItem.isChecked = true
        fragmentClass = when (menuItem.itemId) {
            R.id.homeNav -> HomeFragment::class.java
//            android.R.id.nav_second_fragment -> SecondFragment::class.java
//            android.R.id.nav_third_fragment -> ThirdFragment::class.java
            else -> HomeFragment::class.java
        }
        try {
            fragment = fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insert the fragment by replacing any existing fragment
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.fragment_placeHolder, fragmentClass.newInstance())
        ft.commit()

        // Set action bar title
        title = menuItem.title
    }

    private fun menu()
    {
//        topAppBar.setNavigationOnClickListener {
//            drawerLayout.close()
//            true
//        }
        navigationView.setNavigationItemSelectedListener (this)



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        selectDrawerItem(item)
        return true
    }


}
