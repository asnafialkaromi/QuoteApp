package com.nafi.quoteapp.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.nafi.quoteapp.data.datasource.QuoteApiDataSource
import com.nafi.quoteapp.data.datasource.QuotesDataSource
import com.nafi.quoteapp.data.repository.QuoteRepository
import com.nafi.quoteapp.data.repository.QuoteRepositoryImpl
import com.nafi.quoteapp.data.source.network.services.QuoteApiServices
import com.nafi.quoteapp.databinding.ActivityMainBinding
import com.nafi.quoteapp.utils.proceedWhen

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        val s = QuoteApiServices.invoke()
        val ds: QuotesDataSource = QuoteApiDataSource(s)
        val rp: QuoteRepository = QuoteRepositoryImpl(ds)
        GenericViewModelFactory.create(MainViewModel(rp))
    }

    private val adapterQuote: AdapterQuote by lazy {
        AdapterQuote()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpList()
        observeData()
    }

    private fun setUpList() {
        binding.rvContent.adapter = this@MainActivity.adapterQuote
    }

    private fun observeData() {
        viewModel.getAllQuotes().observe(this) { result ->
            result.proceedWhen(
                doOnLoading = {
                    binding.rvContent.isVisible = false
                    binding.pbState.isVisible = true
                    binding.textEmpty.isVisible = false
                    binding.textError.isVisible = false
                },
                doOnSuccess = {
                    binding.rvContent.isVisible = true
                    binding.pbState.isVisible = false
                    binding.textEmpty.isVisible = false
                    binding.textError.isVisible = false
                    result.payload?.let {
                        adapterQuote.submitData(it)
                    }
                },
                doOnEmpty = {
                    binding.rvContent.isVisible = false
                    binding.pbState.isVisible = false
                    binding.textEmpty.isVisible = true
                    binding.textError.isVisible = false
                },
                doOnError = {
                    binding.rvContent.isVisible = false
                    binding.pbState.isVisible = false
                    binding.textEmpty.isVisible = false
                    binding.textError.isVisible = false
                    result.payload?.let {
                        adapterQuote.submitData(it)
                    }
                }
            )
        }
    }
}
