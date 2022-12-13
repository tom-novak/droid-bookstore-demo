package com.tom_novak.droidbookstore.ui.booksearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tom_novak.droidbookstore.R
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.ui.composable.ContentSection
import com.tom_novak.droidbookstore.ui.view.BookGridTile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: BookSearchViewModel = hiltViewModel(),
    onDetailClick: (BookRemote) -> Unit,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        val uiState by viewModel.uiState.collectAsState()

        ContentSection(
            modifier = Modifier.padding(padding),
            label = stringResource(id = R.string.new_books)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items = uiState.books, key = {
                    it.isbn10 ?: it.isbn13 ?: 0
                }) { book -> // TODO compute unique item id
                    BookGridTile(imgSrc = book.image,
                        title = book.title,
                        authors = book.authors,
                        price = book.price,
                        onClick = {
                            onDetailClick(book)
                        })
                }
            }
        }
    }
}