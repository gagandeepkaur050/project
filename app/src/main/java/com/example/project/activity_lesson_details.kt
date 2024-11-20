package com.example.project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.project.R

class LessonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)
    }

    fun onWatchLessonClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=W6NZfCO5SIk"))
        startActivity(intent)
    }

    fun onMarkAsCompleteClicked(view: View) {
        // Mark this lesson as completed in SharedPreferences
    }
}
