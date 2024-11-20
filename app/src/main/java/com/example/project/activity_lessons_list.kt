package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Lesson(val number: Int, val name: String, val length: String, val isCompleted: Boolean)

class LessonsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLessons)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val lessons = getLessons()
        val adapter = LessonsAdapter(lessons)
        recyclerView.adapter = adapter
    }

    private fun getLessons(): List<Lesson> {
        // Create a list of lessons
        return listOf(
            Lesson(1, "Lesson 1", "10:30", false),
            Lesson(2, "Lesson 2", "15:45", true),
            Lesson(3, "Lesson 3", "12:00", false),
            Lesson(4, "Lesson 4", "8:30", true),
            Lesson(5, "Lesson 5", "14:00", false)
        )
    }
}
