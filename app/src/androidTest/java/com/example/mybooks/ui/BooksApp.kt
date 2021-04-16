package com.example.mybooks.ui

import com.example.mybooks.app.di.DaggerTestApplicationComponent
import com.example.mybooks.app.di.TestApplicationModule

open class BooksApp : App() {

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        graph = DaggerTestApplicationComponent.builder()
                .testApplicationModule(TestApplicationModule(this))
                .build()
    }
}