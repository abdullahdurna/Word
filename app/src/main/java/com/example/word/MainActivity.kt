package com.example.word

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.word.Adapter.WordAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var wordAdapter: WordAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var translateButton: Button

    // ViewModel örneğini oluştur
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        wordAdapter = WordAdapter(emptyList())
        recyclerView.adapter = wordAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        editText = findViewById(R.id.edittext)
        translateButton = findViewById(R.id.button)

        // ViewModel örneğini başlat
        val wordDao = WordRoomDatabase.getDatabase(application).wordDao()
        val repository = WordRepository(wordDao)
        val viewModelFactory = WordViewModelFactory(repository)
        wordViewModel = ViewModelProvider(this, viewModelFactory).get(WordViewModel::class.java)

        // Kelime listesi canlı verisine bir gözlemci ekleyin
        wordViewModel.allWords.observe(this, { words ->
            words?.let { wordAdapter.updateWords(it) }
        })

        translateButton.setOnClickListener {
            val original = editText.text.toString()
            val translation = translateWord(original)

            if (translation != null) {
                val word = Word(original = original, translation = translation)
                GlobalScope.launch(Dispatchers.IO) { wordViewModel.insert(word) }
            } else {
                Toast.makeText(applicationContext, "Translation not found", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // Bu fonksiyonu veritabanından çeviri yapacak şekilde güncellemeliyiz
    fun translateWord(original: String): String? {
        return wordViewModel.getTranslation(original)
    }

}