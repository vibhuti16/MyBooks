package com.example.mybooks.app.di.module

import com.example.mybooks.ui.App
import dagger.Module
import dagger.Provides
import com.example.mybooks.utils.NetworkSystem
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module

class TestUtilsModule {
    @Provides
    @Singleton
    fun provideNetworkSystem(app: App) =
            NetworkSystem(app)

    @Provides
    @Singleton
    fun provideCorutineContext() =
        Dispatchers.Default as CoroutineContext

}