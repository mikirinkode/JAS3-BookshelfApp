package com.mikirinkode.bookshelfapp.fake

import com.mikirinkode.bookshelfapp.data.InterfaceBookRepository
import com.mikirinkode.bookshelfapp.model.BookVolume

class FakeBookRepository : InterfaceBookRepository {
    override suspend fun getBooks(): List<BookVolume?>? {
        return FakeDataSource.bookVolumeList
    }

}