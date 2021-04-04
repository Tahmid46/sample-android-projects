package com.example.roompersistencelibrary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.roompersistencelibrary.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupActionBarWithNavController(
            this,
            getNavController()
        )
    }

    /*
       For handling back button
     */
    override fun onSupportNavigateUp(): Boolean = NavigationUI.navigateUp(
        getNavController(),
        null
    )

    private fun getNavController(): NavController = Navigation.findNavController(
        this,
        R.id.fragment
    )
}