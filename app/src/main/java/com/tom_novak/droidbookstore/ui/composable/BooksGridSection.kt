package com.tom_novak.droidbookstore.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.fake.FakeBooks.Companion.fakeBooks
import com.tom_novak.droidbookstore.ui.theme.DroidBookStoreTheme
import com.tom_novak.droidbookstore.ui.view.BookGridTile

@Composable
fun BooksGridSection(
    modifier: Modifier = Modifier,
    label: String? = null,
    books: List<BookRemote> = emptyList(),
    onDetailClick: (BookRemote) -> Unit = {},
) {
    ContentSection(
        modifier = modifier, label = label
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(items = books, key = {
                it.isbn10 ?: it.isbn13 ?: 0 // TODO compute unique item id
            }) { book ->
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

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun PreviewBookGridSection() {
    DroidBookStoreTheme() {
        BooksGridSection(
            label = "Test label",
            books = fakeBooks.subList(0, 3),
        )
    }

}