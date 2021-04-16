package com.example.mybooks.data.source.network

import com.example.mybooks.domain.model.Response
import com.example.mybooks.domain.model.books.BooksListResponse

open abstract class INetworkDataSource() {

     /**
     * BOOKS LIST
     */
    abstract suspend fun getBooksList(query: String): Response<BooksListResponse>


}