package com.mikirinkode.bookshelfapp.data

import com.mikirinkode.bookshelfapp.model.BookVolume
import com.mikirinkode.bookshelfapp.network.BookApiService

private const val KEY = "" // TODO: MOVE
interface InterfaceBookRepository {
    suspend fun getBooks(): List<BookVolume?>?
}

class BookRepository(
    private val apiService: BookApiService
) : InterfaceBookRepository{
    override suspend fun getBooks(): List<BookVolume?>? {
        val response = apiService.getBooks(
            searchQuery = "programming",
            apiKey = KEY
        )
        return response.items
    }

}