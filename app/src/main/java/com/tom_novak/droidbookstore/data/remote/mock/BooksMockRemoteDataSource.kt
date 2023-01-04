package com.tom_novak.droidbookstore.data.remote.mock

import com.tom_novak.droidbookstore.data.BookDataSource
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote
import com.tom_novak.droidbookstore.fake.FakeBooks.Companion.fakeBooks

class BooksMockRemoteDataSource : BookDataSource {

    override suspend fun search(query: String, page: Int?): Result<BookSearchPageRemote> {
        return Result.success(
            BookSearchPageRemote(
                page = page ?: 1,
                total = fakeBooks.size,
                books = fakeBooks.subList(0, 40)
            )
        )
    }

    override suspend fun newBooks(): Result<BookSearchPageRemote> {
        return Result.success(
            BookSearchPageRemote(
                page = 1,
                total = 200,
                books = fakeBooks.subList(0, 200),
            )
        )
    }

    override suspend fun getBookDetail(bookId: String): Result<BookRemote> {
        val book = fakeBooks.find { bookRemote ->
            bookRemote.isbn10.equals(bookId) || bookRemote.isbn13.equals(
                bookId
            )
        }
        return Result.success(book ?: BookRemote(error = "Book not found"))
    }

}