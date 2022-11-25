package com.tom_novak.droidbookstore.data.remote.model

data class BookRemote(
    val title: String? = null,
    val subtitle: String? = null,
    val authors: String? = null,
    val publisher: String? = null,
    val isbn10: String? = null,
    val isbn13: String? = null,
    val pages: String? = null,
    val year: String? = null,
    val rating: String? = null,
    val description: String? = null,
    val price: String? = null,
    val image: String? = null,
    val url: String? = null,
    val pdf: Map<String, String> = emptyMap(),
)