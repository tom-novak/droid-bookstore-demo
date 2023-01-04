package com.tom_novak.droidbookstore.ui.booksearch

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tom_novak.droidbookstore.data.BookRepository
import com.tom_novak.droidbookstore.data.BookSearchPagingSource
import com.tom_novak.droidbookstore.data.remote.model.BookRemote

class BookSearchPagerManager(private val bookRepository: BookRepository) {
    var pagingSource: BookSearchPagingSource? = null

    fun newPager(query: String, itemsPerPage: Int = ITEMS_PER_PAGE): Pager<Int, BookRemote> {
        invalidatePagingSource()
        val pagingConfig = PagingConfig(pageSize = itemsPerPage, maxSize = MAX_SIZE)

        val pagingSourceFactory = {
            val source = BookSearchPagingSource(
                bookRepository = bookRepository, query = query, itemsPerPage = itemsPerPage
            )
            pagingSource = source
            source
        }

        return Pager(config = pagingConfig, pagingSourceFactory = pagingSourceFactory)
    }

    fun invalidatePagingSource() {
        pagingSource?.invalidate()
    }

    companion object {
        const val ITEMS_PER_PAGE = 10
        const val MAX_SIZE = 50
    }
}