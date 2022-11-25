package com.tom_novak.droidbookstore.data

import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

interface BookDataSource {
    fun search(query: String, page: Int?): BookSearchPageRemote

    fun newBooks(): BookSearchPageRemote

    fun getBookDetail(bookId: String): BookRemote
}