package com.example.project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LessonsAdapter(private val lessons: MutableList<Lesson>,val lessonlist:MutableList<Boolean>) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_lesson, parent, false)
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

            //lessonStatus.text = if (lesson.isCompleted) "Completed" else "Not Completed"
            lessonStatus.text = if (lessonlist[lesson.number-1]) "Completed" else "Not Completed"

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, LessonDetailsActivity::class.java)
                intent.putExtra("lesson_number", lesson.number)
                intent.putExtra("lesson_name", lesson.name)
                intent.putExtra("lesson_url", lesson.url)
                itemView.context.startActivity(intent)
            }
        }
    }

    // To refresh the data in the adapter
    fun updateLessons(newLessons: List<Lesson>) {
        lessons.clear()
        lessons.addAll(newLessons)
        notifyDataSetChanged()
    }
}
