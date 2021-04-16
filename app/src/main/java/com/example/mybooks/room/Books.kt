package com.example.mybooks.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Books")
data class Books(@PrimaryKey @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "title") val title: String,
                 @ColumnInfo(name = "description") val description : String,
                 @ColumnInfo(name = "imageLinks") val imageLinks: String,
                 @ColumnInfo(name = "author") val author : String,
                 @ColumnInfo(name = "amount") val amount : String,
                 @ColumnInfo(name = "currencyCode") val currencyCode : String,
                 @ColumnInfo(name = "webReaderLink") val webReaderLink : String) {
}