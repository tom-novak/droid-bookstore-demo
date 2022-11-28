package com.tom_novak.droidbookstore.data.remote.mock

import com.tom_novak.droidbookstore.data.BookDataSource
import com.tom_novak.droidbookstore.data.remote.model.BookRemote
import com.tom_novak.droidbookstore.data.remote.model.BookSearchPageRemote

class BooksMockRemoteDataSource : BookDataSource {

    val fakeBooks: List<BookRemote> = List(1000)
    { index ->
        BookRemote(
            error = "0",
            title = "Book $index",
            subtitle = "Fancy book $index",
            description = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean id metus id velit ullamcorper pulvinar. Nullam sit amet magna in magna gravida vehicula. Nunc auctor. Nunc tincidunt ante vitae massa. Nulla non arcu lacinia neque faucibus fringilla. Duis sapien nunc, commodo et, interdum suscipit, sollicitudin et, dolor. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Donec iaculis gravida nulla. Aenean vel massa quis mauris vehicula lacinia. Aliquam erat volutpat. Etiam bibendum elit eget erat. Nullam rhoncus aliquam metus. Nunc auctor.",
            authors = "Collective of authors",
            publisher = "Books publisher",
            isbn10 = "isbn10$index",
            isbn13 = "isbn13$index",
            pages = "${index * 2}",
            year = "2022",
            rating = "4.5",
            price = "100.00",
            image = "",
            url = "",
            pdf = emptyMap(),
        )
    }

    override suspend fun search(query: String, page: Int?): BookSearchPageRemote {
        return BookSearchPageRemote(
            page = page ?: 1,
            total = fakeBooks.size,
            books = fakeBooks.subList(0, 40)
        )
    }

    override suspend fun newBooks(): BookSearchPageRemote {
        return BookSearchPageRemote(
            page = 1,
            total = 200,
            books = fakeBooks.subList(0, 200),
        )
    }

    override suspend fun getBookDetail(bookId: String): BookRemote {
        val book = fakeBooks.find { bookRemote ->
            bookRemote.isbn10.equals(bookId) || bookRemote.isbn13.equals(
                bookId
            )
        }
        return book ?: BookRemote(error = "Book not found")
    }

}