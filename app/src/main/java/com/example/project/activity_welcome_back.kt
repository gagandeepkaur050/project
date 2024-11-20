package com.example.project

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.project.EnterNameActivity
import com.example.project.LessonsListActivity
import com.example.project.R

class WelcomeBackActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_back)

        val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "Guest")
        val progress = getLessonProgress()

        findViewById<TextView>(R.id.textViewWelcome).text = "Welcome, $userName!"
        findViewById<TextView>(R.id.textViewProgress).text = "Your Progress: $progress/5 lessons completed"
    }

    private fun getLessonProgress(): Int {
        val sharedPreferences = getSharedPreferences("lessonPrefs", MODE_PRIVATE)
        var progress = 0
        for (i in 0 until 5) {
            if (sharedPreferences.getBoolean("lesson_$i", false)) {
                progress++
            }
        }
        return progress
    }

    fun onGoToLessonsClicked(view: View) {
        val intent = Intent(this, LessonsListActivity::class.java)
        startActivity(intent)
    }

    fun onResetDataClicked(view: View) {
        val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(this, EnterNameActivity::class.java)
        startActivity(intent)
        finish()
    }
}
