package com.nafi.quoteapp.data.repository

import com.nafi.quoteapp.data.datasource.QuotesDataSource
import com.nafi.quoteapp.data.mapper.toQuotes
import com.nafi.quoteapp.data.model.Quote
import com.nafi.quoteapp.utils.ResultWrapper
import com.nafi.quoteapp.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getRandomQuotes(): Flow<ResultWrapper<List<Quote>>>
}

class QuoteRepositoryImpl(private val dataSource: QuotesDataSource) : QuoteRepository {
    override fun getRandomQuotes(): Flow<ResultWrapper<List<Quote>>> {
        return proceedFlow {
            dataSource.getRandomQuotes().toQuotes()
        }
    }

}