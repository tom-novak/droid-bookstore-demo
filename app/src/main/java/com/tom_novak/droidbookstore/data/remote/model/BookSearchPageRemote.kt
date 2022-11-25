package com.tom_novak.droidbookstore.data.remote.model

class BookSearchPageRemote(
    val page: Int = 0,
    val total: Int = 0,
    val books: List<BookRemote> = listOf(),
)