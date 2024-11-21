package com.example.project

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LessonDetailsActivity : AppCompatActivity() {

    private var lessonUrl: String = ""
    private var lessonNumber: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)

        // Retrieve lesson data passed from the previous activity
        lessonNumber = intent.getIntExtra("lesson_number", -1)
        val lessonName = intent.getStringExtra("lesson_name") ?: "Unknown Lesson"
        lessonUrl = intent.getStringExtra("lesson_url") ?: ""

        // Set the lesson details on the UI
        val lessonNameTextView: TextView = findViewById(R.id.lessonNameTextView)
        val lessonNumberTextView: TextView = findViewById(R.id.lessonNumberTextView)

        lessonNameTextView.text = "Lesson: $lessonName"
        lessonNumberTextView.text = "Lesson Number: $lessonNumber"
    }

    // When the user clicks to mark the lesson as complete
    fun onMarkAsCompleteClicked(view: View) {
        val sharedPreferences = getSharedPreferences("lesson_status", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("lesson_$lessonNumber", true) // Mark the lesson as complete
        editor.apply()

        Toast.makeText(this, "Lesson marked as complete", Toast.LENGTH_SHORT).show()

        // Finish the activity and return to the previous screen
        finish()
    }

    // When the user clicks to watch the lesson
    fun onWatchLessonClicked(view: View) {
        if (lessonUrl.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(lessonUrl))
            startActivity(intent)
        } else {
            Toast.makeText(this, "No URL available for this lesson", Toast.LENGTH_SHORT).show()
        }
    }

    // Back button handler
    fun onButtonBack(view: View) {
        finish()
    }
}
