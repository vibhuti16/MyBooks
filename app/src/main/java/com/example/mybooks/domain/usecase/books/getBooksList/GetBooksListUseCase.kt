package com.example.mybooks.domain.usecase.books.getBooksList

import com.example.mybooks.data.repository.BooksRepository
import com.example.mybooks.domain.model.Response
import com.example.mybooks.domain.model.books.BooksListResponse
import com.example.mybooks.domain.usecase.base.BaseUseCase
import com.example.mybooks.domain.usecase.books.getBooksList.GetBooksListRequest

open class GetBooksListUseCase(val repository: BooksRepository) : BaseUseCase<GetBooksListRequest, BooksListResponse>() {

    override suspend fun run(): Response<BooksListResponse> {
        return repository.getBooksList(request!!)
    }
}