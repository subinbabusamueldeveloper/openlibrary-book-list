package subin.openlibrary.booklist.feature_book_list.presentation.compoents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import subin.openlibrary.booklist.feature_book_list.domain.model.Book
import subin.openlibrary.booklist.feature_book_list.presentation.BookListUiState

@Composable
fun BookListContent(
    uiState: BookListUiState,
    onBookSelected: (Book) -> Unit
) {
    when (uiState) {

        is BookListUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is BookListUiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.message ?: "Something went wrong",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        is BookListUiState.Success -> {
            val books = uiState.books
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(
                    items = books,
                    key = { it.id }  // make sure `id` exists in your Book model
                ) { book ->
                    BookCard(
                        book = book,
                        onClick = { onBookSelected(book) }
                    )
                }
            }
        }
    }
}
