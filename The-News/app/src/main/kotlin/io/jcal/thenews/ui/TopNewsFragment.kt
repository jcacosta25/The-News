package io.jcal.thenews.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.jcal.thenews.R
import io.jcal.thenews.databinding.FragmentTopNewsBinding
import io.jcal.thenews.ui.adapter.ArticleAdapter
import io.jcal.thenews.ui.model.ListArticlesUiModel
import io.jcal.thenews.ui.viewmodel.NewsViewModel
import javax.inject.Inject

class TopNewsFragment : BaseFragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentTopNewsBinding
    private val adapter: ArticleAdapter = ArticleAdapter()
    private var recyclerInstanceState: Parcelable? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_news, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        if (savedInstanceState != null) {
            val articles = savedInstanceState.getParcelable<ListArticlesUiModel>(BUNDLE_TOP_NEWS)
            viewModel.setHeadlineNews(articles)
            recyclerInstanceState = savedInstanceState.getParcelable<Parcelable>(
                BUNDLE_TOP_NEWS_INSTANCE_STATE
            )
        }
        binding.topNewsRv.adapter = adapter
        binding.topNewsRv.setHasFixedSize(true)
        viewModel.headlineNews().observe(this, Observer { response ->

            adapter.addAll(response.articles)
            recyclerInstanceState?.let {
                binding.topNewsRv.layoutManager!!.onRestoreInstanceState(it)
                recyclerInstanceState = null
            }
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.headlineNews().value?.let {
            outState.putParcelable(BUNDLE_TOP_NEWS, it)
            binding.topNewsRv.layoutManager?.let { manager ->
                outState.putParcelable(
                    BUNDLE_TOP_NEWS_INSTANCE_STATE,
                    manager.onSaveInstanceState()
                )
            }

        }
    }

    companion object {
        private const val BUNDLE_TOP_NEWS = "top_news"
        private const val BUNDLE_TOP_NEWS_INSTANCE_STATE = "top_news_instance_state"
    }
}
