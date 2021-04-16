package com.example.mybooks.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class LocalBooksRepository (private val booksDao: BooksDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allBooks: Flow<List<Books>> = booksDao.getBooks()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Books) {
        booksDao.insert(word)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        booksDao.deleteAll()
    }
}