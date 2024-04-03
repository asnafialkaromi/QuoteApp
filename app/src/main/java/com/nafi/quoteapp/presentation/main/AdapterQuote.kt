package com.nafi.quoteapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nafi.quoteapp.data.model.Quote
import com.nafi.quoteapp.databinding.LayoutItemQuoteBinding

class AdapterQuote : RecyclerView.Adapter<AdapterQuote.QuoteViewHolder>() {

    private val dataDiffer =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(
                oldItem: Quote,
                newItem: Quote
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Quote,
                newItem: Quote
            ): Boolean {
                return oldItem.hashCode() == oldItem.hashCode()
            }

        })

    fun submitData(data: List<Quote>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            LayoutItemQuoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }

    class QuoteViewHolder (private val binding: LayoutItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Quote) {
            setQuotes(data)
        }

        private fun setQuotes(data: Quote) {
            with(binding) {
                textQuote.text = data.quote
                textQuoteCharacter.text = data.character
                textQuoteAnime.text = data.anime
            }
        }
    }
}
