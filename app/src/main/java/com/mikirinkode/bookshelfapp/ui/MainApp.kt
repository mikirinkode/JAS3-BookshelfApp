package com.mikirinkode.bookshelfapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mikirinkode.bookshelfapp.ui.screen.BookViewModel
import com.mikirinkode.bookshelfapp.ui.screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {

    val viewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Programming Books") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
            )
        }
    ) {
        HomeScreen(viewModel = viewModel, modifier = Modifier.padding(it))
    }
}