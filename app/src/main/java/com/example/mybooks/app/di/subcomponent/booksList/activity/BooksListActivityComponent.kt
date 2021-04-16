package com.example.mybooks.app.di.subcomponent.booksList.activity

import dagger.Subcomponent
import com.example.mybooks.ui.booksList.activity.BooksListActivity

@Subcomponent(modules = arrayOf(BooksListActivityModule::class))
interface BooksListActivityComponent {
    fun injectTo(activity: BooksListActivity)
}