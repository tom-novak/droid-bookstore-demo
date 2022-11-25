package com.tom_novak.droidbookstore.data.local

import com.tom_novak.droidbookstore.data.BookDataSource
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

class BooksLocalDataSource : BookDataSource {
    override fun search(query: String, page: Int?): BookSearchPageRemote {
        TODO("Not yet implemented")
    }

    override fun newBooks(): BookSearchPageRemote {
        TODO("Not yet implemented")
    }

    override fun getBookDetail(bookId: String): BookRemote {
        TODO("Not yet implemented")
    }
}