package com.tom_novak.droidbookstore.di

import com.tom_novak.droidbookstore.data.BookDataSource
import com.tom_novak.droidbookstore.data.BookRepository
import com.tom_novak.droidbookstore.data.DefaultBookRepository
import com.tom_novak.droidbookstore.data.local.BooksLocalDataSource
import com.tom_novak.droidbookstore.data.remote.mock.BooksMockRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteBooksDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalBooksDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesBookRepository(
        @LocalBooksDataSource localDataSource: BookDataSource,
        @RemoteBooksDataSource remoteDataSource: BookDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): BookRepository {
        return DefaultBookRepository(
            remoteDataSource,
            localDataSource,
            ioDispatcher,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteBooksDataSource
    @Provides
    fun providesMockRemoteDataSource(): BookDataSource {
        return BooksMockRemoteDataSource()
    }

    @Singleton
    @LocalBooksDataSource
    @Provides
    fun providesBookStoreDataSource(): BookDataSource {
        return BooksLocalDataSource()
    }
}