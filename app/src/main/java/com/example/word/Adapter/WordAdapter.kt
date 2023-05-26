package com.example.word.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.word.R
import com.example.word.Word

class WordAdapter(private var wordList: List<Word>) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val originalTextView: TextView = itemView.findViewById(R.id.original_text_view)
        val translationTextView: TextView = itemView.findViewById(R.id.translation_text_view)

    }
    fun updateWords(newWords: List<Word>) {
        wordList = newWords
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        // TODO: Inflate the item layout and return a ViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentWord = wordList[position]
        holder.originalTextView.text = currentWord.original
        holder.translationTextView.text = currentWord.translation
        // TODO: Bind the original word and translation to the TextViews in the ViewHolder
    }


    override fun getItemCount(): Int {
        // Return the size of the word list
        return wordList.size
    }
}
