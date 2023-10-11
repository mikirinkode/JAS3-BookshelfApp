package com.mikirinkode.bookshelfapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mikirinkode.bookshelfapp.ui.theme.BookshelfAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Bookshelf") })
        }
    ){
        BookGridScreen(modifier = modifier.padding(it))
    }
}

@Composable
fun BookGridScreen(
    modifier: Modifier = Modifier
) {
    
}

@Composable
fun BookItemCard(
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
fun HomeScreenPreview() {
    BookshelfAppTheme {
        Surface {
            HomeScreen()
        }
    }
}