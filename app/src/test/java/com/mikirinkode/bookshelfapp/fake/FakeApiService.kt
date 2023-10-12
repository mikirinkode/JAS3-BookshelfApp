package com.mikirinkode.bookshelfapp.fake

import com.mikirinkode.bookshelfapp.model.BookSearchResponse
import com.mikirinkode.bookshelfapp.model.BookVolume
import com.mikirinkode.bookshelfapp.network.BookApiService

class FakeApiService: BookApiService {
    override suspend fun getBooks(searchQuery: String, apiKey: String): BookSearchResponse {
        return FakeDataSource.response
    }

}