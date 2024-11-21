package com.example.project

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Lesson(
    val number: Int,
    val name: String,
    val length: String,
    val isCompleted: Boolean,
    val url: String // Defining URL as a String
)



class LessonsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLessons)
        recyclerView.layoutManager = LinearLayoutManager(this)



        val sharedPreferences = getSharedPreferences("lessons", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonString = sharedPreferences.getString("LESSON_LIST",null)
        var lessonList:MutableList<Boolean> = mutableListOf()


        if(jsonString != null){
            val type = object : TypeToken<List<Boolean>>() {}.type
            var tempList:List<Boolean> = gson.fromJson(jsonString, type)
            lessonList = tempList.toMutableList()
        }

        val lessons = getLessons().toMutableList()  // Convert to mutable list
        val adapter = LessonsAdapter(lessons,lessonList)
        recyclerView.adapter = adapter
    }

    private fun getLessons(): List<Lesson> {
        //val sharedPreferences = getSharedPreferences("lesson_status", Context.MODE_PRIVATE)
        return listOf(
            Lesson(1, "Lesson 1", "10:30", false, "https://www.example.com/lesson1"),
            Lesson(2, "Lesson 2", "15:45", false, "https://www.example.com/lesson2"),
            Lesson(3, "Lesson 3", "12:00", false, "https://www.example.com/lesson3"),
            Lesson(4, "Lesson 4", "8:30", false, "https://www.example.com/lesson4"),
            Lesson(5, "Lesson 5", "14:00", false, "https://www.example.com/lesson5")
        )
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("lessons", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonString = sharedPreferences.getString("LESSON_LIST",null)
        var lessonList:MutableList<Boolean> = mutableListOf()

        if(jsonString != null){
            val type = object : TypeToken<List<Boolean>>() {}.type
            var tempList:List<Boolean> = gson.fromJson(jsonString, type)
            lessonList = tempList.toMutableList()
        }

        val lessons = getLessons().toMutableList()  // Convert to mutable list
        val adapter = LessonsAdapter(lessons,lessonList)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLessons)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    fun onButtonBack(view: View) {
        finish()
    }
}
