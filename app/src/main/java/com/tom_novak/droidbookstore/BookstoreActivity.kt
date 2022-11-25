package com.tom_novak.droidbookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tom_novak.droidbookstore.ui.theme.DroidBookStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookstoreActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidBookStoreTheme {
                BookstoreNavGraph()
            }
        }
    }
}