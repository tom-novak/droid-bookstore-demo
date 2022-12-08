package com.tom_novak.droidbookstore.ui.booksearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tom_novak.droidbookstore.ui.model.Book
import com.tom_novak.droidbookstore.ui.view.BookGridTile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: BookSearchViewModel = hiltViewModel(),
    onDetailClick: (Book) -> Unit,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            BookGridTile(
                imgSrc = "https://itbook.store/img/books/9781617294136.png",
                title = "New Book",
                authors = "Fancy Author",
                price = "$200.00",
            )
        }
    }
}