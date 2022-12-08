package com.tom_novak.droidbookstore.data

import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultBookRepository(
    private val booksRemoteDataSource: BookDataSource,
    private val bookLocalDataSource: BookDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BookRepository {

    override suspend fun search(query: String, page: Int): Result<BookSearchPageRemote> {
        // TODO(implement remote api call with store data in local cache)
        return booksRemoteDataSource.search(query, page)
    }

    override suspend fun newBooks(): Result<BookSearchPageRemote> {
        // TODO(implement remote api call with store data in local cache)
        return booksRemoteDataSource.newBooks()
    }

    override suspend fun getBookDetail(bookId: String): Result<BookRemote> {
        // TODO(implement remote api call with store data in local cache)
        return booksRemoteDataSource.getBookDetail(bookId)
    }
}