package subin.openlibrary.booklist.feature_book_list.domain.repository

import kotlinx.coroutines.flow.Flow
import subin.openlibrary.booklist.core.common.Resource
import subin.openlibrary.booklist.feature_book_list.domain.model.Book
import subin.openlibrary.booklist.feature_book_list.domain.model.BookListTab

interface BookRepository {
    fun getBooks(tab: BookListTab): Flow<Resource<List<Book>>>
}