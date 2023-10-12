package com.mikirinkode.bookshelfapp

import com.mikirinkode.bookshelfapp.data.BookRepository
import com.mikirinkode.bookshelfapp.fake.FakeApiService
import com.mikirinkode.bookshelfapp.fake.FakeBookRepository
import com.mikirinkode.bookshelfapp.fake.FakeDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookRepositoryTest {
    @Test
    fun bookRepository_getBooks_verifyResponse() =
        runTest {
            val repository = BookRepository(
                apiService = FakeApiService()
            )
            assertEquals(
                FakeDataSource.bookVolumeList,
                repository.getBooks()
            )
        }
}