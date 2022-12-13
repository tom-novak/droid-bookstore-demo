package com.tom_novak.droidbookstore.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tom_novak.droidbookstore.R

@Composable
fun BookGridTile(
    imgSrc: String? = null,
    title: String? = null,
    authors: String? = null,
    price: String? = null,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imgSrc != null)
            AsyncImage(
                model = imgSrc,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.book_image_content_description),
                modifier = Modifier
                    .size(width = 128.dp, height = 128.dp)
            )

        if (title != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (authors != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = authors,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (price != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = price,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
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
fun PreviewBookGridTile() {
    BookGridTile(
        imgSrc = "https://itbook.store/img/books/9781617294136.png",
        title = "New Book",
        authors = "Fancy Author",
        price = "$200.00",
    )
}