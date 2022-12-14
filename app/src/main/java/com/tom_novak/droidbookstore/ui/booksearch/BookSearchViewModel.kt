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
    val newBooks: List<BookRemote> = emptyList(),
    val searchResult: List<BookRemote>? = null,
    val error: Boolean = false,
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
                        newBooks = result.books,
                        loadingBooks = false,
                        error = false,
                    )
                }
            }.onFailure { failure ->
                _uiState.update { it.copy(loadingBooks = false) }
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
                            searchResult = result.books,
                            loadingBooks = false,
                            error = false,
                        )
                    }
                }.onFailure {
                    _uiState.update { it.copy(loadingBooks = false) }
                }
            }
        } else {
            _uiState.update { it.copy(query = query) }
        }
    }

}