package com.treetrack

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.treetrack.databinding.ActivityHomeBinding

class ActivityHome : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#2264F4")))

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        this.onBackPressedDispatcher.addCallback(this, callback)


        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 4 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentActivities,
                R.id.fragmentMyTasks,
                R.id.fragmentMyGroups,
                R.id.fragmentProfile,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

}