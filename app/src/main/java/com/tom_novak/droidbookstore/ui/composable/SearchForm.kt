package com.tom_novak.droidbookstore.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tom_novak.droidbookstore.R
import com.tom_novak.droidbookstore.ui.theme.DroidBookStoreTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    value: String? = null,
    hint: String = stringResource(id = R.string.search_hint),
    onValueChange: (String) -> Unit = {},
    onSubmit: (String) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var query by remember { mutableStateOf(TextFieldValue(value ?: "")) }
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(modifier = Modifier.weight(1f),
            value = query,
            onValueChange = {
                query = it
                onValueChange(it.text)
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() })
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            keyboardController?.hide()
            onSubmit(query.text)
        }) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = stringResource(id = R.string.search_button)
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
fun PreviewSearchForm() {
    DroidBookStoreTheme() {
        SearchForm(
            modifier = Modifier.padding(8.dp),
            value = "test query",
        )
    }
}