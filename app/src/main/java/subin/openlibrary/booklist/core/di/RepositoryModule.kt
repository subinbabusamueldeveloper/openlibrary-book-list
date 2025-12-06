package subin.openlibrary.booklist.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import subin.openlibrary.booklist.feature_book_list.data.repository.BookRepositoryImpl
import subin.openlibrary.booklist.feature_book_list.domain.repository.BookRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookRepository(
        impl: BookRepositoryImpl
    ): BookRepository
}