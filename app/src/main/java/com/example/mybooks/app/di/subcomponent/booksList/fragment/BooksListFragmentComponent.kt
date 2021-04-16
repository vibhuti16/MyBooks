package com.example.mybooks.app.di.subcomponent.booksList.fragment

import dagger.Subcomponent
import com.example.mybooks.ui.booksList.fragment.BooksListFragment

@Subcomponent(modules = arrayOf(BooksListFragmentModule::class))
interface BooksListFragmentComponent {
    fun injectTo(fragment: BooksListFragment)
}