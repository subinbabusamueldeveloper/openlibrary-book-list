package subin.openlibrary.booklist.feature_book_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import subin.openlibrary.booklist.R
import subin.openlibrary.booklist.feature_book_list.presentation.compoents.BookDetailsBottomSheet
import subin.openlibrary.booklist.feature_book_list.presentation.compoents.BookListContent
import subin.openlibrary.booklist.feature_book_list.presentation.compoents.BookListTabs


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    viewModel: BookListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val selectedBook by viewModel.selectedBook.collectAsState()

    val sheetOpen = selectedBook != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            BookListTabs(
                selectedTab = selectedTab,
                onTabSelected = { viewModel.onTabSelected(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            BookListContent(
                uiState = uiState,
                onBookSelected = { book -> viewModel.onBookClick(book) }
            )
        }

        if (sheetOpen && selectedBook != null) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.closeBottomSheet() }
            ) {
                BookDetailsBottomSheet(
                    book = selectedBook!!,
                    onClose = { viewModel.closeBottomSheet() }
                )
            }
        }
    }
}
