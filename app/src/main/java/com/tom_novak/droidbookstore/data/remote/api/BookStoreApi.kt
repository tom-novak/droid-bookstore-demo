package com.tom_novak.droidbookstore.data.remote.api

import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface BookStoreApi {

    companion object {
        val BASE_URL = "https://api.itbook.store/1.0/"
    }

    @GET("search/{query}/{page}")
    suspend fun search(@Path("query") query: String, @Path("page") page: Int?): BookSearchPageRemote

    @GET("new")
    suspend fun new(): BookSearchPageRemote

    @GET("books/{isbn}")
    suspend fun book(@Path("isbn") isbn: String): BookRemote

}