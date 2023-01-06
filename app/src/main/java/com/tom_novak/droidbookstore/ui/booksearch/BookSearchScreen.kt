package com.tom_novak.droidbookstore.ui.booksearch

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tom_novak.droidbookstore.R
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.ui.composable.BooksGridSection
import com.tom_novak.droidbookstore.ui.composable.BooksGridSectionPageable
import com.tom_novak.droidbookstore.ui.composable.PlaceholderGridSection
import com.tom_novak.droidbookstore.ui.composable.SearchForm
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: BookSearchViewModel = hiltViewModel(),
    onDetailClick: (BookRemote) -> Unit,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.logo_text),
                textAlign = TextAlign.Center,
            )

            when {
                uiState.loadingNewBooks -> {
                    PlaceholderGridSection(
                        modifier = Modifier
                            .weight(1f)
                            .shimmer()
                    )
                }
                uiState.items != null -> {
                    BooksGridSectionPageable(modifier = Modifier.weight(1f),
                        label = stringResource(id = R.string.search_results),
                        books = uiState.items!!,
                        onDetailClick = { book ->
                            onDetailClick(book)
                        })

                }
                else -> {
                    uiState.newBooks.fold(onFailure = { failure -> }, onSuccess = { books ->
                        BooksGridSection(modifier = Modifier.weight(1f),
                            label = stringResource(id = R.string.new_books),
                            books = books,
                            onDetailClick = { book ->
                                onDetailClick(book)
                            })
                    })
                }
            }

            SearchForm(
                modifier = Modifier.padding(8.dp),
                value = uiState.query,
                onSubmit = { query ->
                    viewModel.search(query)
                })
        }
    }
}