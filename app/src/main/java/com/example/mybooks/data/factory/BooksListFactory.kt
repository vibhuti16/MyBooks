package com.example.mybooks.data.factory

import android.content.Context
import com.google.gson.Gson
import com.example.mybooks.data.utils.Utils
import com.example.mybooks.domain.model.books.BooksListResponse

object BooksListFactory {

    fun createBooksListResponse1(context: Context): BooksListResponse {
        var gson = Gson()
        val json = Utils.readJsonFromAssets(context, "response1.json")
        return gson?.fromJson(json, BooksListResponse::class.java)
    }
}