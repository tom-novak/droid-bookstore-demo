package com.tom_novak.droidbookstore.data

import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

interface BookRepository {

    suspend fun search(query: String, page: Int?): BookSearchPageRemote

    suspend fun newBooks(): BookSearchPageRemote

    suspend fun getBookDetail(bookId: String): BookRemote
}