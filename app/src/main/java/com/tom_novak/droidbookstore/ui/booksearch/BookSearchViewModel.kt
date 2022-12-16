package com.tom_novak.droidbookstore.ui.booksearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tom_novak.droidbookstore.data.BookRepository
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookSearchState(
    val page: Int = 0,
    val query: String = "",
    val loadingBooks: Boolean = false,
    val newBooks: Result<List<BookRemote>> = Result.success(emptyList()),
    val searchResult: Result<List<BookRemote>>? = null,
)

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookSearchState())
    val uiState: StateFlow<BookSearchState> = _uiState.asStateFlow()

    private var loadBooksJob: Job? = null

    init {
        new()
    }

    fun new() {
        _uiState.update { it.copy(loadingBooks = true) }
        loadBooksJob?.cancel()
        loadBooksJob = viewModelScope.launch {
            bookRepository.newBooks().onSuccess { result ->
                _uiState.update {
                    it.copy(
                        newBooks = Result.success(result.books),
                        loadingBooks = false,
                    )
                }
            }.onFailure { failure ->
                _uiState.update {
                    it.copy(
                        loadingBooks = false, newBooks = Result.failure(failure)
                    )
                }
            }

        }
    }

    fun search(query: String) {
        if (query.length > 2) {
            _uiState.update {
                it.copy(
                    loadingBooks = true,
                    query = query,
                    searchResult = null,
                )
            }
            loadBooksJob?.cancel()
            loadBooksJob = viewModelScope.launch {
                bookRepository.search(query = query, page = 0).onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            searchResult = Result.success(result.books),
                            loadingBooks = false,
                        )
                    }
                }.onFailure { failure ->
                    _uiState.update {
                        it.copy(
                            loadingBooks = false, searchResult = Result.failure(failure)
                        )
                    }
                }
            }
        } else {
            _uiState.update { it.copy(query = query) }
        }
    }

}