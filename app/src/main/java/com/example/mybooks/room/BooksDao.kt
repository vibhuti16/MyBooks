package com.example.mybooks.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM Books ORDER BY title ASC")
    fun getBooks(): Flow<List<Books>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Books)

    @Query("DELETE FROM Books")
    suspend fun deleteAll()
}