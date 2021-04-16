package com.example.mybooks.app.di.subcomponent.bookDetails.activity

import dagger.Subcomponent
import com.example.mybooks.ui.bookDetails.activity.BookDetailsActivity

@Subcomponent(modules = arrayOf(BookDetailsActivityModule::class))
interface BookDetailsActivityComponent {
    fun injectTo(activity: BookDetailsActivity)
}