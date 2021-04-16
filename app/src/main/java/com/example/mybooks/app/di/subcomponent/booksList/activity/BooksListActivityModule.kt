package com.example.mybooks.app.di.subcomponent.booksList.activity

import dagger.Module
import com.example.mybooks.app.di.module.ActivityModule
import com.example.mybooks.ui.booksList.activity.BooksListActivity

@Module
class BooksListActivityModule(activity: BooksListActivity) : ActivityModule(activity) {

}