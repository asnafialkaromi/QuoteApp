package com.nafi.quoteapp.data.datasource

import com.nafi.quoteapp.data.source.network.model.QuoteResponse
import com.nafi.quoteapp.data.source.network.services.QuoteApiServices

interface QuotesDataSource {
    suspend fun getRandomQuotes(): List<QuoteResponse>
}

class QuoteApiDataSource(private val service: QuoteApiServices) : QuotesDataSource {
    override suspend fun getRandomQuotes(): List<QuoteResponse> {
        return service.getRandomQuotes()
    }

}