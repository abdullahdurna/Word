package com.example.word

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun getTranslation(original: String): String? {
        return wordDao.getTranslation(original)
    }
}

