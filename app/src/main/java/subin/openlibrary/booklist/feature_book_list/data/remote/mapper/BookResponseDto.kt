package subin.openlibrary.booklist.feature_book_list.data.remote.mapper

import subin.openlibrary.booklist.feature_book_list.data.remote.dto.BookDocDto
import subin.openlibrary.booklist.feature_book_list.domain.model.Book

fun BookDocDto.toDomain(): Book? {
    val work = this.work ?: return null

    return Book(
        id = work.key?.substringAfterLast("/") ?: "",
        title = work.title.orEmpty(),
        author = work.authorNames?.firstOrNull().orEmpty(),
        firstPublishYear = work.firstPublishYear,
        year = work.firstPublishYear,
        coverId = work.coverId,
        loggedDate = this.loggedDate
    )
}