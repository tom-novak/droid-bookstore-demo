package com.tom_novak.droidbookstore.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tom_novak.droidbookstore.ui.theme.DroidBookStoreTheme

@Composable
fun ContentSection(
    modifier: Modifier = Modifier,
    label: String? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        if (label != null) Text(modifier = Modifier.padding(8.dp), text = label)
        content()
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
fun PreviewContentSection() {
    DroidBookStoreTheme() {
        ContentSection(
            label = "Test label",
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Red),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Content")
            }
        }
    }
}

@Preview(
    name = "Without labe - Light",
    showBackground = true,
)
@Preview(
    name = "Without label - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun PreviewContentSectionWithoutLabel() {
    DroidBookStoreTheme {
        ContentSection {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Blue),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Content", color = Color.White)
            }
        }
    }
}