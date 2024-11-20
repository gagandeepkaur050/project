package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LessonsAdapter(private val lessons: List<Lesson>) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.bind(lesson)
    }

    override fun getItemCount() = lessons.size

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val lessonName: TextView = itemView.findViewById(R.id.textViewLessonName)
        private val lessonStatus: TextView = itemView.findViewById(R.id.textViewLessonStatus)

        fun bind(lesson: Lesson) {
            lessonName.text = "${lesson.number}. ${lesson.name} - ${lesson.length}"
            lessonStatus.text = if (lesson.isCompleted) "Completed" else "Not Completed"
        }
    }
}
