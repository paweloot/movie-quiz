package com.paweloot.quiz

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (navController.currentDestination?.id == R.id.quizFragment) {
            showLeaveQuizDialog()
            return true
        }

        navController.navigateUp()
        return true
    }

    fun showLeaveQuizDialog() {
        val alert = AlertDialog.Builder(this)
            .setMessage("Are you sure you want to leave the quiz?")
            .setPositiveButton("Yes") { dialogInterface, _ ->
                dialogInterface.dismiss()
                navController.navigateUp()
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }

        alert.show()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }
}
