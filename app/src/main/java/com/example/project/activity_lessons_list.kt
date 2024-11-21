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
    val url: String,
    val description: String
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
            Lesson(1, "HTML Tutorial", "10:30", false, "https://www.example.com/lesson1", "HTML is the standard markup language for creating Web pages.\n"),
            Lesson(2, "CSS", "15:45", false, "https://www.example.com/lesson2","CSS is the language we use to style an HTML document.\n" +
                    "\n" +
                    "CSS describes how HTML elements should be displayed.\n" +
                    "\n" +
                    "This tutorial will teach you CSS from basic to advanced."),
            Lesson(3, "JavaScript ", "12:00", false, "https://www.example.com/lesson3","JavaScript is the world's most popular programming language.\n" +
                    "\n" +
                    "JavaScript is the programming language of the Web.\n" +
                    "\n" +
                    "JavaScript is easy to learn.\n" +
                    "\n" +
                    "This tutorial will teach you JavaScript from basic to advanced.\n" +
                    "\n"),
            Lesson(4, "React ", "8:30", false, "https://www.example.com/lesson4","React is a JavaScript library for building user interfaces.\n" +
                    "\n" +
                    "React is used to build single-page applications.\n" +
                    "\n" +
                    "React allows us to create reusable UI components."),
            Lesson(5, "Kotlin", "14:00", false, "https://www.example.com/lesson5","Kotlin is a modern, trending programming language.\n" +
                    "\n" +
                    "Kotlin is easy to learn, especially if you already know Java (it is 100% compatible with Java).\n" +
                    "\n" +
                    "Kotlin is used to develop Android apps, server side apps, and much more.")
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
