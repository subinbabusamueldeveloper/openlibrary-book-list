package subin.openlibrary.booklist.feature_book_list.data.datasource

import jakarta.inject.Inject
import subin.openlibrary.booklist.feature_book_list.data.remote.api.BookApi
import subin.openlibrary.booklist.feature_book_list.data.remote.dto.BookResponseDto

class BookRemoteDataSource @Inject constructor(
    private val api: BookApi
) {
    suspend fun getBooks(category: String): BookResponseDto {
        return api.getBooks(category)
    }
}