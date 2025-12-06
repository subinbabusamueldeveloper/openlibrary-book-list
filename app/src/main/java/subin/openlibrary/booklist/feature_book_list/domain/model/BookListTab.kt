package subin.openlibrary.booklist.feature_book_list.domain.model

enum class BookListTab(val title: String, val category: String) {
    WantToRead("Want To Read", "want-to-read"),
    CurrentlyReading("Currently Reading", "currently-reading"),
    AlreadyRead("Already Read", "already-read")
}