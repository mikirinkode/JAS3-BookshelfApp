package com.mikirinkode.bookshelfapp.data

import com.mikirinkode.bookshelfapp.network.BookApiService
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val bookRepository: InterfaceBookRepository
}


/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val bookRepository: InterfaceBookRepository by lazy {
        BookRepository(retrofitService)
    }
}