package subin.openlibrary.booklist.feature_book_list.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import subin.openlibrary.booklist.feature_book_list.data.remote.dto.BookResponseDto

interface BookApi {

    @GET("people/mekBot/books/{category}.json")
    suspend fun getBooks(
        @Path("category") category: String
    ): BookResponseDto
}