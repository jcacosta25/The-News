package io.jcal.thenews.ui

import android.os.Bundle
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
import io.jcal.thenews.ui.viewmodel.NewsViewModel
import javax.inject.Inject

class TopNewsFragment : BaseFragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentTopNewsBinding
    private val adapter: ArticleAdapter = ArticleAdapter()

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

        viewModel.headlineNews().observe(this, Observer { response ->
            binding.topNewsRv.adapter = adapter
            adapter.addAll(response.articles)
        })
    }
}
