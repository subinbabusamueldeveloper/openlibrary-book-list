package subin.openlibrary.booklist.feature_book_list.presentation

import subin.openlibrary.booklist.feature_book_list.domain.model.Book

sealed class BookListUiState {
    object Loading : BookListUiState()
    data class Success(val books: List<Book>) : BookListUiState()
    data class Error(val message: String?) : BookListUiState()
}