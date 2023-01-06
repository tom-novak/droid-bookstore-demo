package com.tom_novak.droidbookstore.data.remote.model

import java.util.concurrent.atomic.AtomicInteger

data class BookRemote(
    val error: String? = null,
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
) {
    companion object {
        private var instance: AtomicInteger = AtomicInteger(1)
    }

    val id = instance.incrementAndGet()
}