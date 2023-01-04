package com.tom_novak.droidbookstore.data.remote

import com.tom_novak.droidbookstore.data.BookDataSource
import com.tom_novak.droidbookstore.data.remote.api.BookStoreApi
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

class BooksRemoteDataSource(private val api: BookStoreApi) : BookDataSource {
    override suspend fun search(query: String, page: Int?): Result<BookSearchPageRemote> {
        return try {
            Result.success(api.search(query, page))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun newBooks(): Result<BookSearchPageRemote> {
        return try {
            Result.success(api.new())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBookDetail(bookId: String): Result<BookRemote> {
        return try {
            Result.success(api.book(bookId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}