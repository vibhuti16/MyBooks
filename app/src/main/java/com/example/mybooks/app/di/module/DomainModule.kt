package com.example.mybooks.app.di.module

import dagger.Module
import dagger.Provides
import com.example.mybooks.data.repository.BooksRepository
import com.example.mybooks.domain.usecase.books.getBooksList.GetBooksListUseCase
import javax.inject.Singleton


@Module
class DomainModule {

    /**
     * BOOKS
     */
    @Provides
    @Singleton
    fun provideGetBooksListUseCase(repository: BooksRepository) = GetBooksListUseCase(repository)

}