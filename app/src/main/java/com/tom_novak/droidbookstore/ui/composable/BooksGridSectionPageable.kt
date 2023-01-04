package com.tom_novak.droidbookstore.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.ui.view.BookGridTile
import kotlinx.coroutines.flow.Flow

@Composable
fun BooksGridSectionPageable(
    modifier: Modifier = Modifier,
    label: String? = null,
    books: Flow<PagingData<BookRemote>>,
    onDetailClick: (BookRemote) -> Unit = {},
) {
    LabeledSection(
        modifier = modifier, label = label
    ) {
        val lazyPagingItems = books.collectAsLazyPagingItems()

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(lazyPagingItems.itemCount) { bookId ->
                val book = lazyPagingItems[bookId]!!
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
