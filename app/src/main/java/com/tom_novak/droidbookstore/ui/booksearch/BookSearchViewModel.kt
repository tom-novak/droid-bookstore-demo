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
    val query: String? = null,
    val loadingBooks: Boolean = false,
    val books: List<BookRemote> = emptyList(),
    val error: Boolean = false,
)

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookSearchState())
    val uiState: StateFlow<BookSearchState> = _uiState.asStateFlow()

    private var searchBookJob: Job? = null

    init {
        new()
    }

    fun new() {
        viewModelScope.launch {
            bookRepository.newBooks().onSuccess { result ->
                _uiState.update {
                    it.copy(
                        books = result.books
                    )
                }
            }.onFailure { failure ->

            }

        }
    }

    fun search(query: String) {
        searchBookJob?.cancel()
        searchBookJob = viewModelScope.launch {
            // TODO
            _uiState.update {
                it.copy(
                    // TODO
                )
            }
        }
    }

}