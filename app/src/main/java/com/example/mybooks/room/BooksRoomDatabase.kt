package com.example.mybooks.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Database migrations are beyond the scope of this codelab, so exportSchema has been set to false here, in order to avoid a build warning.
@Database(entities = arrayOf(Books::class), version = 1, exportSchema = false)
public abstract class BooksRoomDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BooksRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): BooksRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(BooksDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    class BooksDatabaseCallback (
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.booksDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: BooksDao) {
            // Delete all content here.
            wordDao.deleteAll()

            // Add sample words.
//            var word = Books("Hello")
//            wordDao.insert(word)
//            word = Books("World!")
//            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }
}