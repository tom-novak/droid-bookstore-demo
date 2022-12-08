package com.tom_novak.droidbookstore.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tom_novak.droidbookstore.data.BookDataSource
import com.tom_novak.droidbookstore.data.BookRepository
import com.tom_novak.droidbookstore.data.DefaultBookRepository
import com.tom_novak.droidbookstore.data.local.BooksLocalDataSource
import com.tom_novak.droidbookstore.data.remote.BooksRemoteDataSource
import com.tom_novak.droidbookstore.data.remote.api.BookStoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun providesBooksRemoteDataSource(
        api: BookStoreApi,
    ): BookDataSource {
        return BooksRemoteDataSource(api)
    }

    @Singleton
    @LocalBooksDataSource
    @Provides
    fun providesBooksLocalDataSource(): BookDataSource {
        return BooksLocalDataSource()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesBookStoreApi(): BookStoreApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BookStoreApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(BookStoreApi::class.java)
    }
}