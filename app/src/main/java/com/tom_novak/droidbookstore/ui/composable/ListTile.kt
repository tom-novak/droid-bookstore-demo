package com.tom_novak.droidbookstore.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tom_novak.droidbookstore.R

@Composable
fun ListTile(title: String?, subtitle: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Fake image",
            modifier = Modifier
                .size(width = 24.dp, height = 24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title ?: "",
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle ?: "",
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Fake image",
                modifier = Modifier.size(width = 24.dp, height = 24.dp)
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
fun PreviewListTile() {
    ListTile(
        title = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit",
        subtitle = "Ahoj"
    )
}