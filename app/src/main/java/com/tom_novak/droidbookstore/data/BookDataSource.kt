package com.tom_novak.droidbookstore.data

import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

interface BookDataSource {
    suspend fun search(query: String, page: Int): Result<BookSearchPageRemote>

    suspend fun newBooks(): Result<BookSearchPageRemote>

    suspend fun getBookDetail(bookId: String): Result<BookRemote>
}