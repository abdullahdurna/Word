package com.example.word

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * from words ORDER BY original ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM words")
    fun getAll(): List<Word>

    @Insert
    suspend fun insert(word: Word)


    @Query("SELECT translation FROM words WHERE original = :original LIMIT 1")
    fun getTranslation(original: String): String?
}