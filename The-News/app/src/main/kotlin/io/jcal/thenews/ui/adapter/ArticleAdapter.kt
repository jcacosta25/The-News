package io.jcal.thenews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.jcal.thenews.R
import io.jcal.thenews.databinding.ArticleNewsItemBinding
import io.jcal.thenews.ui.model.ArticleUiModel


class ArticleAdapter(private val articles: MutableList<ArticleUiModel> = mutableListOf()) :
    RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.article_news_item, parent,
                false
            )
        )

    fun addAll(articles: List<ArticleUiModel>) {
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}

class ArticleViewHolder(private val binding: ArticleNewsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(article: ArticleUiModel) {
        binding.article = article
        binding.executePendingBindings()
    }
}