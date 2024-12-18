package com.example.project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

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
        val tickIcon: ImageView = itemView.findViewById(R.id.tickIcon)
        fun bind(lesson: Lesson) {
            lessonName.text = "${lesson.number}. ${lesson.name} - ${lesson.length}"

            //lessonStatus.text = if (lesson.isCompleted) "Completed" else "Not Completed"
            if (lessonlist[lesson.number-1]) {
                tickIcon.visibility = View.VISIBLE
                lessonStatus.text = ""
            }
                else {
                tickIcon.visibility = View.GONE
                lessonStatus.text = "Not Completed"
            }

            lessonName.setOnClickListener {
                // Create an Intent to navigate to LessonDetailsActivity
                val intent = Intent(itemView.context, LessonDetailsActivity::class.java)
                // Pass additional data like lesson number, name, and URL
                intent.putExtra("lesson_number", lesson.number)  // Pass lesson number
                intent.putExtra("lesson_name", lesson.name)      // Pass lesson name
                intent.putExtra("lesson_url", lesson.url)        // Pass lesson URL (if available)
                intent.putExtra("lesson_description", lesson.description)
                intent.putExtra("lesson_length", lesson.length)
                itemView.context.startActivity(intent)
                // Show a Toast message
                Toast.makeText(itemView.context, "Navigating to Lesson ${lesson.number}", Toast.LENGTH_SHORT).show()
            }
        }
//        fun updateLessons(newLessons: List<Lesson>) {
//            lessons.clear()
//            lessons.addAll(newLessons)
//            notifyDataSetChanged()
//        }
    }
}
