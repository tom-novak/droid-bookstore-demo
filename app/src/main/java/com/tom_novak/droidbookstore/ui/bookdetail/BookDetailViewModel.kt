package com.tom_novak.droidbookstore.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tom_novak.droidbookstore.data.IBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookDetailState(
    val loadingBook: Boolean = false,
    val error: Boolean = false,
    val isbn: String? = null,
    val title: String? = null,
    val description: String? = null,
)

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    //private val repository: IBookRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookDetailState())
    val uiState: StateFlow<BookDetailState> = _uiState.asStateFlow()

    private var loadBookDetailJob: Job? = null

    fun init(book: String) {
        _uiState.update {
            it.copy(
                title = book,
            )
        }
    }

    fun loadBookDetail() {
        loadBookDetailJob?.cancel()
        loadBookDetailJob = viewModelScope.launch {
            _uiState.update {
                // TODO
                it.copy(
                    // TODO
                )
            }
        }
    }
}