package com.example.mybooks.domain.usecase.books.getBooksList

import com.example.mybooks.domain.usecase.base.BaseRequest

class GetBooksListRequest(var query: String) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}