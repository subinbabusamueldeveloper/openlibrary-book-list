package subin.openlibrary.booklist.feature_book_list.data.repository

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import subin.openlibrary.booklist.core.common.Resource
import subin.openlibrary.booklist.core.network.safeApiCall
import subin.openlibrary.booklist.feature_book_list.data.datasource.BookRemoteDataSource
import subin.openlibrary.booklist.feature_book_list.data.remote.mapper.toDomain
import subin.openlibrary.booklist.feature_book_list.domain.model.Book
import subin.openlibrary.booklist.feature_book_list.domain.model.BookListTab
import subin.openlibrary.booklist.feature_book_list.domain.repository.BookRepository

class BookRepositoryImpl @Inject constructor(
    private val remote: BookRemoteDataSource
) : BookRepository {

    override fun getBooks(tab: BookListTab): Flow<Resource<List<Book>>> = flow {

        val category = tab.category

        val result = safeApiCall {
            remote.getBooks(category)
        }

        when (result) {
            is Resource.Success -> {
                val books = result.data.readingLogEntries
                    ?.mapNotNull { it.toDomain() }
                    .orEmpty()

                emit(Resource.Success(books))
            }

            is Resource.Error -> {
                emit(Resource.Error(result.message, result.throwable))
            }

        }
    }
}