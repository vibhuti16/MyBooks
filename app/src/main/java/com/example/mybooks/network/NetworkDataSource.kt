package com.example.mybooks.network

import android.content.Context
import com.example.mybooks.BuildConfig
import com.example.mybooks.data.source.network.GoogleBooksAPI
import com.example.mybooks.data.source.network.INetworkDataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.example.mybooks.domain.model.Response
import com.example.mybooks.domain.model.books.BooksListResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class NetworkDataSource(val context: Context) : INetworkDataSource() {

    /**
     * GET BOOKS LIST
     */
    override suspend fun getBooksList(query: String): Response<BooksListResponse> {
        val googleBooksAPI = initRetrofitGoogleBooksAPI()
        try {
            val getBooksList =
                googleBooksAPI.getBooksList(query)
                    .await()

            return Response.Success(getBooksList)
        } catch (e: Exception) {
            return Response.Error(e)
        }
    }


    private fun initRetrofitGoogleBooksAPI(): GoogleBooksAPI {
        val retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.GOOGLE_API_BOOKS_URL_BASE)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }.build()

        val googleBooksAPI = retrofit.create(GoogleBooksAPI::class.java)
        return googleBooksAPI
    }


    var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request()
                val response = chain.proceed(request)

                // todo deal with the issues the way you need to
                if (response.code() == 500) {
                    return response
                }

                return response
            }
        })
        .build()

}