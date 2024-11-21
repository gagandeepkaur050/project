package com.example.project
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.project.R

class LessonDetailsActivity : AppCompatActivity() {

    private var lessonUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details) // Ensure you're using the correct layout file

        // Retrieve the lesson data passed from the previous activity (LessonsListActivity)
        val lessonNumber = intent.getIntExtra("lesson_number", -1)
        val lessonName = intent.getStringExtra("lesson_name") ?: "Unknown Lesson"
        lessonUrl = intent.getStringExtra("lesson_url") ?: ""

        // Find the TextViews to display the lesson details
        val lessonNameTextView: TextView = findViewById(R.id.lessonNameTextView) // Correct ID reference
        val lessonNumberTextView: TextView = findViewById(R.id.lessonNumberTextView) // Correct ID reference

        // Set the lesson details in the TextViews
        lessonNameTextView.text = "Lesson: $lessonName"

        lessonNumberTextView.text = "Lesson Number: $lessonNumber"
        // Optional: Show other lesson details (e.g., length, completion status) if needed
    }

    // When the user clicks to watch the lesson, open the URL in the browser
    fun onWatchLessonClicked(view: View) {
        if (lessonUrl.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(lessonUrl))
            startActivity(intent)
        } else {
            Toast.makeText(this, "No URL available for this lesson", Toast.LENGTH_SHORT).show()
        }
    }

    // You can add functionality to mark the lesson as completed here
    fun onMarkAsCompleteClicked(view: View) {
        val sharedPreferences = getSharedPreferences("lesson_status", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val lessonNumber = intent.getIntExtra("lesson_number", -1)
        editor.putBoolean("lesson_$lessonNumber", true)
        editor.apply()
        Toast.makeText(this, "Lesson marked as complete", Toast.LENGTH_SHORT).show()
        finish()
    }
}