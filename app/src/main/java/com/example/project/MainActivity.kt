package com.example.project

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
    }

    // Method called when the button is clicked
    fun onSubmitClicked(view: View) {
        val userInput = editText.text.toString() // Get the text from EditText
        textView.text = "Hello, $userInput!" // Update the TextView
    }
}
