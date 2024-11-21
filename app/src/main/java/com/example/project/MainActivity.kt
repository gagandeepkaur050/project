package com.example.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        //val username = sharedPreferences.getString("user_name", "").toString()
        val isFirstTime = sharedPreferences.getBoolean("isFirstTime",true)

        if (isFirstTime == false) {
            val intent = Intent(this, WelcomeBackActivity::class.java)
            startActivity(intent)
            finish()
        } else{
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        }
    }

    fun onContinueClicked(view: View) {
        val userName = findViewById<EditText>(R.id.editTextName).text.toString()

        if (userName.isNotEmpty()) {
            // Save to SharedPreferences
            val sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("user_name", userName)
            editor.putBoolean("isFirstTime", false)
            editor.apply()

            // Navigate to Lessons List screen
            val intent = Intent(this, WelcomeBackActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

