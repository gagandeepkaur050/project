package com.example.project

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class EnterNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)
    }

    fun onContinueClicked(view: View) {
        val userName = findViewById<EditText>(R.id.editTextName).text.toString()

        if (userName.isNotEmpty()) {
            // Save to SharedPreferences
            val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("user_name", userName)
            editor.putBoolean("isFirstTime", false)
            editor.apply()

            // Navigate to Lessons List screen
            val intent = Intent(this, LessonsListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
