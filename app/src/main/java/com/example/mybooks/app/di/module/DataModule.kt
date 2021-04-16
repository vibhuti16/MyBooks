package com.example.mybooks.app.di.module

import dagger.Module
import dagger.Provides
import com.example.mybooks.data.source.network.INetworkDataSource
import com.example.mybooks.network.NetworkDataSource
import com.example.mybooks.ui.App
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun provideNetworkDataSource(appContext: App) =
            NetworkDataSource(appContext) as INetworkDataSource


}
