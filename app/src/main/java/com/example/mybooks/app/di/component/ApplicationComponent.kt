package com.example.mybooks.app.di.component

import com.example.mybooks.app.di.module.*
import dagger.Component
import com.example.mybooks.app.di.subcomponent.bookDetails.activity.BookDetailsActivityComponent
import com.example.mybooks.app.di.subcomponent.bookDetails.activity.BookDetailsActivityModule
import com.example.mybooks.app.di.subcomponent.booksList.activity.BooksListActivityComponent
import com.example.mybooks.app.di.subcomponent.booksList.activity.BooksListActivityModule
import com.example.mybooks.app.di.subcomponent.booksList.fragment.BooksListFragmentComponent
import com.example.mybooks.app.di.subcomponent.booksList.fragment.BooksListFragmentModule
import com.example.mybooks.app.di.viewmodel.ViewModelFactoryModule
import com.example.mybooks.app.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        RepositoryModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelFactoryModule::class,
        UtilsModule::class,
        ViewModelModule::class
    )
)
interface ApplicationComponent {
    /**
     * UI - ACTIVITY
     */

    /**
     * BOOKS LIST
     */
    fun plus(module: BooksListActivityModule): BooksListActivityComponent
    fun plus(module: BooksListFragmentModule): BooksListFragmentComponent

    /**
     * BOOKS DETAILS
     */
    fun plus(module: BookDetailsActivityModule): BookDetailsActivityComponent

}