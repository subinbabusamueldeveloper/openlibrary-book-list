package subin.openlibrary.booklist.feature_book_list.data.remote.dto

import com.squareup.moshi.Json

data class BookDocDto(
    @Json(name = "work")
    val work: WorkDto? = null,
    @Json(name = "logged_edition")
    val loggedEdition: String? = null,
    @Json(name = "logged_date")
    val loggedDate: String? = null
)