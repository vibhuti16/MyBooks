package com.example.mybooks.app.di.module

import dagger.Module
import dagger.Provides
import com.example.mybooks.data.repository.BooksRepository
import com.example.mybooks.data.source.network.INetworkDataSource
import com.example.mybooks.room.BooksDao
import javax.inject.Singleton

@Module
class RepositoryModule{
    @Provides
    @Singleton
    fun provideBooksRepository(
        networkDataSource: INetworkDataSource
    ) = BooksRepository(networkDataSource)

}