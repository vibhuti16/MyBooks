package com.example.mybooks.app.di


import com.example.mybooks.app.navigator.Navigator
import com.example.mybooks.ui.App
import com.example.mybooks.ui.BooksApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class TestApplicationModule(val app: BooksApp){
    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator()


}