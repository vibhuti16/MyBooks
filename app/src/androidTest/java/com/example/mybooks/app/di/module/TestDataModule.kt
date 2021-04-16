package com.example.mybooks.app.di.module

import com.example.mybooks.data.source.network.INetworkDataSource
import com.example.mybooks.data.source.network.TestNetworkDataSource
import com.example.mybooks.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestDataModule {

    @Provides @Singleton
    fun provideNetworkDataSource(appContext: App) =
            TestNetworkDataSource() as INetworkDataSource


}
