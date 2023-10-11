package com.mikirinkode.bookshelfapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mikirinkode.bookshelfapp.R
import com.mikirinkode.bookshelfapp.model.BookVolume
import com.mikirinkode.bookshelfapp.ui.component.ErrorCard
import com.mikirinkode.bookshelfapp.ui.component.LoadingIndicator
import com.mikirinkode.bookshelfapp.ui.theme.BookshelfAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: BookViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        viewModel.bookState.let { state ->
            when (state) {
                is UiState.Initial -> {
                    viewModel.getBooks()
                }

                is UiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is UiState.Error -> {
                    ErrorCard(retryAction = viewModel::getBooks)
                }

                is UiState.Success -> {
                    val data = state.bookVolumes
                    if (data.isNullOrEmpty()) {
                        Text("No Data", modifier = Modifier.align(Alignment.Center))
                    } else {
                        BookGridScreen(
                            list = data
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookGridScreen(
    list: List<BookVolume?>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = list) { bookVolume ->
            if (bookVolume != null) {
                BookItemCard(
                    bookVolume = bookVolume,
                    modifier = modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun BookItemCard(
    bookVolume: BookVolume,
    modifier: Modifier = Modifier
) {

    val categories = (bookVolume.volumeInfo?.categories)?.joinToString(" #", "#")

    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(
                            bookVolume.volumeInfo?.imageLinks?.thumbnail?.replace("http", "https")
                                ?: ""
                        )
                        .crossfade(true).build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.ic_image),
                    contentDescription = "Book Thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium.copy(
                                topEnd = ZeroCornerSize,
                                bottomStart = ZeroCornerSize
                            )
                        )
                        .align(Alignment.TopStart)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "${bookVolume.volumeInfo?.averageRating ?: 0}",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Column() {
                    if (categories != null){
                        Text("$categories", color = MaterialTheme.colorScheme.primary)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("Price: IDR${bookVolume.saleInfo?.listPrice?.amount ?: " -"}", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    BookshelfAppTheme {
        Surface {
//            HomeScreen()
        }
    }
}