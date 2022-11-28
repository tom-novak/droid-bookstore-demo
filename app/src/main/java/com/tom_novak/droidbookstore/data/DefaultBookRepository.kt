package com.tom_novak.droidbookstore.data

import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

class DefaultBookRepository: BookRepository {
    override suspend fun search(query: String, page: Int?): BookSearchPageRemote {
        TODO("Not yet implemented")
    }

    override suspend fun newBooks(): BookSearchPageRemote {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetail(bookId: String): BookRemote {
        TODO("Not yet implemented")
    }
}