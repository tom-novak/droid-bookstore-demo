package com.tom_novak.droidbookstore.data.local

import com.tom_novak.droidbookstore.data.BookDataSource
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

class BooksLocalDataSource : BookDataSource {
    override suspend fun search(query: String, page: Int?): Result<BookSearchPageRemote> {
        TODO("Not yet implemented")
    }

    override suspend fun newBooks(): Result<BookSearchPageRemote> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetail(bookId: String): Result<BookRemote> {
        TODO("Not yet implemented")
    }
}