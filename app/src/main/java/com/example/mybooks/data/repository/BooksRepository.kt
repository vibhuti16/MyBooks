package com.example.mybooks.data.repository

import androidx.annotation.WorkerThread
import com.example.mybooks.data.source.network.INetworkDataSource
import com.example.mybooks.domain.model.Response
import com.example.mybooks.domain.model.books.BooksListResponse
import com.example.mybooks.domain.usecase.books.getBooksList.GetBooksListRequest
import com.example.mybooks.room.Books
import com.example.mybooks.room.BooksDao
import kotlinx.coroutines.flow.Flow

class BooksRepository(
    private val networkDataSource: INetworkDataSource
) {

    val TAG = BooksRepository::class.java.simpleName

    /***********************************************************************************************
     * GET BOOKS LIST
     **********************************************************************************************/
    suspend fun getBooksList(request: GetBooksListRequest): Response<BooksListResponse> {
        val response = networkDataSource.getBooksList(request.query)
        return response
    }
}