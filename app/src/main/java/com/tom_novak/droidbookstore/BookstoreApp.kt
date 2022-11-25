package com.tom_novak.droidbookstore

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tom_novak.droidbookstore.ui.theme.DroidBookStoreTheme
import com.tom_novak.droidbookstore.ui.model.CommonItem
import com.tom_novak.droidbookstore.ui.view.CommonList

@Preview(
    name = "Light Mode",
    showBackground = true,
    widthDp = 320
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun DefaultPreview() {
    DroidBookStoreTheme() {
        CommonList(
            listOf(
                CommonItem(title = "Book 1", subtitle = "Author 1"),
                CommonItem(title = "Book 2", subtitle = "Author 2"),
            )
        )
    }
}