package com.example.mybooks.app.di.module

import com.example.mybooks.app.di.viewmodel.ViewModelFactoryModule
import dagger.Module
import dagger.Provides
import com.example.mybooks.app.navigator.Navigator
import com.example.mybooks.ui.App
import javax.inject.Singleton

@Module
class ApplicationModule(val app: App) {
    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator()
}