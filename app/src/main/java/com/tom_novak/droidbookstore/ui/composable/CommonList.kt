package com.tom_novak.droidbookstore.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.tom_novak.droidbookstore.ui.model.CommonItem

@Composable
fun CommonList(books: List<CommonItem>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(books) { book ->
            ListTile(
                title = book.title,
                subtitle = book.subtitle,
            )
        }
    }
}