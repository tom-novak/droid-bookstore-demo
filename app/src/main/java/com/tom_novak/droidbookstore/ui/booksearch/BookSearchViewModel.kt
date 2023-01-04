package com.tom_novak.droidbookstore.ui.booksearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tom_novak.droidbookstore.data.BookRepository
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class BookSearchState(
    val page: Int = 0,
    val query: String = "",
    val loadingNewBooks: Boolean = false,
    val newBooks: Result<List<BookRemote>> = Result.success(emptyList()),
    val items: Flow<PagingData<BookRemote>>? = null,
)

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val bookSearchPagerManager: BookSearchPagerManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookSearchState())
    val uiState: StateFlow<BookSearchState> = _uiState.asStateFlow()

    private var loadBooksJob: Job? = null

    init {
        new()
    }

    fun new() {
        _uiState.update { it.copy(loadingNewBooks = true) }
        loadBooksJob?.cancel()
        loadBooksJob = viewModelScope.launch {
            bookRepository.newBooks().onSuccess { result ->
                _uiState.update {
                    it.copy(
                        newBooks = Result.success(result.books),
                        loadingNewBooks = false,
                    )
                }
            }.onFailure { failure ->
                _uiState.update {
                    it.copy(
                        loadingNewBooks = false, newBooks = Result.failure(failure)
                    )
                }
            }

        }
    }

    fun search(query: String) {
        _uiState.update { it.copy(query = query) }
        if (query.length > 2) {
            val pager = bookSearchPagerManager.newPager(query = query)
            _uiState.update {
                it.copy(
                    items = pager.flow.cachedIn(viewModelScope)
                )
            }
        }
    }

}