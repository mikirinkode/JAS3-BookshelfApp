package com.mikirinkode.bookshelfapp.fake

import com.mikirinkode.bookshelfapp.model.BookSearchResponse
import com.mikirinkode.bookshelfapp.model.BookVolume


object FakeDataSource {
    val bookVolumeList = listOf(
        BookVolume()
    )
    val response: BookSearchResponse = BookSearchResponse(items = bookVolumeList)
}