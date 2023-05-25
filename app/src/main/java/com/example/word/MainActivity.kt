package com.example.word

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var wordAdapter:WordAdapter
    private lateinit var recyclerView: RecyclerView
    private var wordList = mutableListOf<Word>()
    private lateinit var textView: TextView
    private lateinit var translateButton: Button

    val translations = mapOf(
        "apple" to "elma",
        "banana" to "muz",
        "cherry" to "kiraz",
        "car" to "araba",
        "beautiful" to "guzel",
        "handsome" to "yakısıklı",
        // Add more words here
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        wordAdapter = WordAdapter(emptyList())


        recyclerView.adapter = wordAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        textView = findViewById(R.id.textview)
        translateButton = findViewById(R.id.button)

        translateButton.setOnClickListener {
            val original = textView.text.toString()
            val translation = translateWord(original)
            val word = Word(original, translation)
            wordList.add(word)
            wordAdapter.notifyDataSetChanged()
        }


    }
    fun translateWord(original: String): String {
        return translations[original] ?: "Translation not found"
    }

}