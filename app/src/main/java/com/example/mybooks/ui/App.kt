package com.example.mybooks.ui

import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import com.example.mybooks.app.di.component.ApplicationComponent
import com.example.mybooks.app.di.component.DaggerApplicationComponent
import com.example.mybooks.app.di.module.ApplicationModule
import com.example.mybooks.app.di.module.RepositoryModule
import com.example.mybooks.room.BooksRoomDatabase
import com.example.mybooks.room.LocalBooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class App : MultiDexApplication() {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { BooksRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { LocalBooksRepository(database.booksDao()) }
    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()

        initLeakCanary()


    }

    private fun initializeDagger() {
        graph = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    fun getApplicationComponent() = graph
}