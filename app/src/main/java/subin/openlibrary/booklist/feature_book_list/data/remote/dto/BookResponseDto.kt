package subin.openlibrary.booklist.feature_book_list.data.remote.dto

import com.squareup.moshi.Json


data class BookResponseDto(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "numFound")
    val numFound: Int? = null,
    @Json(name = "reading_log_entries")
    val readingLogEntries: List<BookDocDto>? = null
)