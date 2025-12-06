package subin.openlibrary.booklist.feature_book_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import subin.openlibrary.booklist.core.common.Resource
import subin.openlibrary.booklist.feature_book_list.domain.model.Book
import subin.openlibrary.booklist.feature_book_list.domain.model.BookListTab
import subin.openlibrary.booklist.feature_book_list.domain.usecase.GetBookListUseCase


@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(BookListTab.WantToRead)
    val selectedTab: StateFlow<BookListTab> = _selectedTab.asStateFlow()

    private val _uiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading)
    val uiState: StateFlow<BookListUiState> = _uiState.asStateFlow()

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook: StateFlow<Book?> = _selectedBook.asStateFlow()

    init {
        loadBooks(BookListTab.WantToRead)
    }

    fun onTabSelected(tab: BookListTab) {
        if (tab == _selectedTab.value) return
        _selectedTab.value = tab
        loadBooks(tab)
    }

    fun onBookClick(book: Book) {
        _selectedBook.value = book
    }

    fun closeBottomSheet() {
        _selectedBook.value = null
    }

    private fun loadBooks(tab: BookListTab) {
        _uiState.value = BookListUiState.Loading
        getBookListUseCase(tab)
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.value = BookListUiState.Success(resource.data)
                    }

                    is Resource.Error -> {
                        _uiState.value = BookListUiState.Error(resource.message)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}