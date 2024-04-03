package com.nafi.quoteapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nafi.quoteapp.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: QuoteRepository) : ViewModel(){
        fun getAllQuotes() = repository.getRandomQuotes().asLiveData(Dispatchers.IO)
}