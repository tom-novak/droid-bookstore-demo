package com.tom_novak.droidbookstore.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun PlaceholderGridTile(
    modifier: Modifier = Modifier,
    showImage: Boolean = true,
    showTitle: Boolean = true,
    showAuthors: Boolean = true,
    showPrice: Boolean = true,
) {
    Box(modifier = modifier) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showImage) Box(modifier = Modifier.padding(8.dp)) {
                Box(
                    modifier = Modifier
                        .size(width = 72.dp, height = 90.dp)
                        .background(Color.Gray)
                )
            }

            if (showTitle) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(width = 160.dp, height = 16.dp)
                        .background(Color.Gray)
                )
            }

            if (showAuthors) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(width = 140.dp, height = 16.dp)
                        .background(Color.Gray)
                )
            }

            if (showPrice) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(width = 60.dp, height = 20.dp)
                        .background(Color.Gray)
                )
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
fun PreviewBPlaceholderGridTile() {
    PlaceholderGridTile(
        modifier = Modifier.shimmer(),
        showAuthors = false,
    )
}