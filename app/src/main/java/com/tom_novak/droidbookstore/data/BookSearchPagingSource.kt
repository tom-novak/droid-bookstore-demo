package com.tom_novak.droidbookstore.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tom_novak.droidbookstore.data.remote.model.BookRemote

class BookSearchPagingSource(
    val bookRepository: BookRepository, val query: String, val itemsPerPage: Int = 50
) : PagingSource<Int, BookRemote>() {

    override fun getRefreshKey(state: PagingState<Int, BookRemote>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2).coerceAtLeast(1)


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookRemote> {
        return bookRepository.search(query = query, page = params.key ?: 1)
            .fold(onSuccess = { data ->
                Log.d("BookSearchPagingSource", "Page ${data.page} loaded.")
                LoadResult.Page(
                    data = data.books,
                    prevKey = if (data.page > 1) data.page - 1 else null,
                    nextKey = if (data.books.size < itemsPerPage) null else data.page + 1,
                )

            }, onFailure = {
                LoadResult.Error(it)
            })
    }
}