package subin.openlibrary.booklist.feature_book_list.domain.usecase

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import subin.openlibrary.booklist.core.common.Resource
import subin.openlibrary.booklist.feature_book_list.domain.model.Book
import subin.openlibrary.booklist.feature_book_list.domain.model.BookListTab
import subin.openlibrary.booklist.feature_book_list.domain.repository.BookRepository

class GetBookListUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(tab: BookListTab): Flow<Resource<List<Book>>> {
        return repository.getBooks(tab)
    }
}