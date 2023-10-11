package com.mikirinkode.bookshelfapp.ui.screen

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mikirinkode.bookshelfapp.BaseApplication
import com.mikirinkode.bookshelfapp.data.InterfaceBookRepository
import com.mikirinkode.bookshelfapp.model.BookVolume
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UiState{
    object Initial : UiState
    object Loading : UiState
    data class Success(val photos: List<BookVolume?>?) : UiState
    data class Error(val errorMessage: String) : UiState
}

class BookViewModel(
    private val bookRepository: InterfaceBookRepository
) : ViewModel() {

    var bookState: UiState by mutableStateOf(UiState.Initial)
        private set


    fun getBooks() {
        viewModelScope.launch {
            bookState = UiState.Loading
            bookState = try {
                UiState.Success(bookRepository.getBooks())
            } catch (e: IOException) {
                UiState.Error("${e.message}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BaseApplication)
                val bookRepository = application.container.bookRepository
                BookViewModel(bookRepository = bookRepository)
            }
        }
    }
}