package com.example.mybooks.app.di.subcomponent.bookDetails.activity

import dagger.Module
import com.example.mybooks.app.di.module.ActivityModule
import com.example.mybooks.ui.bookDetails.activity.BookDetailsActivity

@Module
class BookDetailsActivityModule(activity: BookDetailsActivity) : ActivityModule(activity) {

}