package com.example.project

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LessonDetailsActivity : AppCompatActivity() {

    private var lessonUrl: String = ""
    private var lessonNumber: Int = -1
    private var lessonDescription: String = ""
    private var lessonLength: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)

        // Retrieve lesson data passed from the previous activity
        lessonNumber = intent.getIntExtra("lesson_number", -1)
        val lessonName = intent.getStringExtra("lesson_name") ?: "Unknown Lesson"
        lessonUrl = intent.getStringExtra("lesson_url") ?: ""
        lessonDescription = intent.getStringExtra("lesson_description") ?: "No description available"
        lessonLength = intent.getStringExtra("lesson_length") ?: "Unknown Length"



        val lessonNameTextView: TextView = findViewById(R.id.lessonNameTextView)
        val lessonDetailTextView: TextView = findViewById(R.id.lessonDetail)
        val lessonLengthTextView: TextView = findViewById(R.id.lessonLengthTextView)
        lessonNameTextView.text = "$lessonNumber: $lessonName"
        lessonDetailTextView.text = lessonDescription
        lessonLengthTextView.text = "Length: $lessonLength"

    }

    // When the user clicks to mark the lesson as complete
    fun onMarkAsCompleteClicked(view: View) {
        val sharedPreferences = getSharedPreferences("lessons", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonString = sharedPreferences.getString("LESSON_LIST",null)
        var lessonList:MutableList<Boolean> = mutableListOf()

        if(jsonString != null){
            val type = object : TypeToken<List<Boolean>>() {}.type
            var tempList:List<Boolean> = gson.fromJson(jsonString, type)
            lessonList = tempList.toMutableList()
        }
        lessonList[lessonNumber-1] = true
        val updatedJsonString = gson.toJson(lessonList)
        sharedPreferences.edit().putString("LESSON_LIST", updatedJsonString).apply()

        Toast.makeText(this, "Lesson marked as complete", Toast.LENGTH_SHORT).show()

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
