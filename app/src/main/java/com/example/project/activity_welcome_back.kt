package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WelcomeBackActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_back)

        val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "Guest")

        val progress = getLessonProgress()
        val CompletedLesson = getLessonCompleted()
        val RemainingLesson = getLessonRemaining()

        findViewById<TextView>(R.id.textViewWelcome).text = "Welcome, $userName!"
        findViewById<TextView>(R.id.textViewProgress).text = "Your Progress: $progress % lessons completed"
        findViewById<TextView>(R.id.LessonCompleted).text = "Lessons Completed: $CompletedLesson "
        findViewById<TextView>(R.id.LessonRemaining).text = "Lessons Remaining: $RemainingLesson"
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "Guest")

        val progress = getLessonProgress()
        val CompletedLesson = getLessonCompleted()
        val RemainingLesson = getLessonRemaining()

        findViewById<TextView>(R.id.textViewWelcome).text = "Welcome, $userName!"
        findViewById<TextView>(R.id.textViewProgress).text = "Your Progress: $progress % lessons completed"
        findViewById<TextView>(R.id.LessonCompleted).text = "Lessons Completed: $CompletedLesson "
        findViewById<TextView>(R.id.LessonRemaining).text = "Lessons Remaining: $RemainingLesson"
    }

    private fun getLessonCompleted(): Int {

        val sharedPreferences = getSharedPreferences("lessons", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonString = sharedPreferences.getString("LESSON_LIST",null)
        var lessonList: List<Boolean> = mutableListOf()
        if(jsonString != null){
            val type = object : TypeToken<List<Boolean>>() {}.type
            lessonList = gson.fromJson(jsonString, type)
        }

       // val sharedPreferences = getSharedPreferences("lessonPrefs", MODE_PRIVATE)
        var CompletedLesson = 0

        // Count the number of completed lessons
        for (i in 0 until 5) {
            //if (sharedPreferences.getBoolean("lesson_$i", false)) {
              if(lessonList[i] == true){
                CompletedLesson++
            }
        }

        return CompletedLesson.toInt()
    }

    private fun getLessonRemaining(): Int {
        val totalLessons = 5
        val completedLessons = getLessonCompleted() // Get the number of completed lessons
        return totalLessons - completedLessons // Remaining lessons = Total lessons - Completed lessons
    }
    private fun getLessonProgress(): Int {
       // val sharedPreferences = getSharedPreferences("lessonPrefs", MODE_PRIVATE)
        var progress = 0

        val sharedPreferences = getSharedPreferences("lessons", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonString = sharedPreferences.getString("LESSON_LIST",null)
        var lessonList: List<Boolean> = mutableListOf()
        if(jsonString != null){
            val type = object : TypeToken<List<Boolean>>() {}.type
            lessonList = gson.fromJson(jsonString, type)
        }

        // Count the number of completed lessons

        for (i in 0 until 5) {
            if (lessonList[i] == true) {
                progress++
            }
        }

        // Calculate the percentage of lessons completed (out of 5)
        val totalLessons = 5
        val progressPercentage = (progress.toDouble() / totalLessons) * 100

        // Return the progress as an integer (percentage)
        return progressPercentage.toInt()
    }


    fun onGoToLessonsClicked(view: View) {
        val intent = Intent(this, LessonsListActivity::class.java)
        startActivity(intent)
    }

    fun onResetDataClicked(view: View) {
        val sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE)
        val sharedPreferences2 = getSharedPreferences("lessons", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val editor2 = sharedPreferences2.edit()
        editor.remove("user_name")
        editor2.remove("LESSON_LIST")
        editor.clear()
        editor2.clear()
        editor.apply()
        editor2.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
