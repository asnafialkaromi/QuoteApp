package com.nafi.quoteapp.data.mapper

import com.nafi.quoteapp.data.model.Quote
import com.nafi.quoteapp.data.source.network.model.QuoteResponse

fun QuoteResponse.toQuote() = Quote(
    id = this.id.orEmpty(),
    quote = this.quote.orEmpty(),
    anime = this.anime.orEmpty(),
    character = this.character.orEmpty()
)

fun Collection<QuoteResponse>.toQuotes() = this.map {
    it.toQuote()
}