package com.example.newsapps.ui.news.fragments.favorite

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapps.R
import com.example.newsapps.base.BaseFragment
import com.example.newsapps.models.Article
import com.example.newsapps.ui.details.DetailsActivity
import com.example.newsapps.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FavoritesViewModel>(R.layout.fragment_favorites), NewsAdapter.OnItemClick {

    override val viewModel by viewModel<FavoritesViewModel>()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var list: MutableList<Article>


    override fun setUpViews() {
        initialization()
        setUpRecyclerView()
    }

    override fun setUpObservers() {
        subscribeToFavorites()
    }

    override fun setUpListeners() {
    }

    private fun initialization() {
        list = mutableListOf()
        newsAdapter = NewsAdapter(list, this)
    }

    override fun onResume() {
        super.onResume()
        list.clear()
        subscribeToFavorites()
    }

    private fun subscribeToFavorites(){
        viewModel.getFavoritesArticles().observe(viewLifecycleOwner, {
            if (it != null){
                list.addAll(it)
                newsAdapter.notifyDataSetChanged()
            }
        })
    }


    private fun setUpRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onItemClick(item: Article) {
        DetailsActivity.instanceActivity(activity, item)
    }

}