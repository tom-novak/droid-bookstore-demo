package com.tom_novak.droidbookstore.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun PlaceholderGridSection(
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    showContent: Boolean = true,
) {
    Column(modifier = modifier) {
        if (showLabel) Box(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .size(width = 160.dp, height = 20.dp)
                    .background(Color.Gray)
            )
        }
        if (showContent) LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(10) {
                PlaceholderGridTile()
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
fun PreviewPlaceholderGridSection() {
    PlaceholderGridSection(modifier = Modifier.shimmer())
}