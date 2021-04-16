package com.example.mybooks.app.di.module

import dagger.Module
import dagger.Provides
import com.example.mybooks.ui.App

import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideCorutineContext() =
        Dispatchers.Default as CoroutineContext

}