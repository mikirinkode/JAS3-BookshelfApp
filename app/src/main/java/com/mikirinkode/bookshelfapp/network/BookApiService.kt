package com.mikirinkode.bookshelfapp.network

import com.mikirinkode.bookshelfapp.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String,
    ): BookSearchResponse
}