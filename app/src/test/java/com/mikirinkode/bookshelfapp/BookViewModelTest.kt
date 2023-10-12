package com.mikirinkode.bookshelfapp

import com.mikirinkode.bookshelfapp.fake.FakeBookRepository
import com.mikirinkode.bookshelfapp.fake.FakeDataSource
import com.mikirinkode.bookshelfapp.rules.TestDispatcherRule
import com.mikirinkode.bookshelfapp.ui.screen.BookViewModel
import com.mikirinkode.bookshelfapp.ui.screen.UiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BookViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookViewModel_getBooks_verifyUiStateSuccess() =
        runTest {
            val viewModel = BookViewModel(
                bookRepository = FakeBookRepository()
            )
            assertEquals(
                UiState.Success(FakeDataSource.bookVolumeList),
                viewModel.bookState
            )
        }
}