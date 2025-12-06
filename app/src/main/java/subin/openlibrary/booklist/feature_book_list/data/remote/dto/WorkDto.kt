package subin.openlibrary.booklist.feature_book_list.data.remote.dto

import com.squareup.moshi.Json

data class WorkDto(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "key")
    val key: String? = null,
    @Json(name = "author_names")
    val authorNames: List<String>? = null,
    @Json(name = "first_publish_year")
    val firstPublishYear: Int? = null,
    @Json(name = "cover_id")
    val coverId: Int? = null
)