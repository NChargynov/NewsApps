    package com.example.newsapps.ui.news.fragments.top_headlines
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapps.R
import com.example.newsapps.base.BaseFragment
import com.example.newsapps.extension.showToast
import com.example.newsapps.helper.PaginationScrollListener
import com.example.newsapps.models.Article
import com.example.newsapps.network.Status
import com.example.newsapps.ui.details.DetailsActivity
import com.example.newsapps.ui.news.NewsViewModel
import com.example.newsapps.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_topheadlines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopHeadlinesFragment : BaseFragment<NewsViewModel>(R.layout.fragment_topheadlines), NewsAdapter.OnItemClick {

    override val viewModel by viewModel<NewsViewModel>()
    private lateinit var list: MutableList<Article>
    private lateinit var newsAdapter: NewsAdapter
    private val linerManager = LinearLayoutManager(activity)

    override fun setUpViews() {
        initialization()
        createRecycler()
    }

    override fun setUpObservers() {
        subscribeToTopHeadlines()
    }

    override fun setUpListeners() {
        nestedScrollListener()
        swipeListener()
    }

    private fun subscribeToTopHeadlines() {
        viewModel.getNewsTopHeadlines().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> setTable(it.data!!.articles)
                Status.ERROR -> showToast(context!!, it.message.toString())
                Status.LOADING -> showToast(context!!, "Данные загружаются")
            }
        })
    }

    private fun setTable(articles: MutableList<Article>) {
        list.addAll(articles)
        newsAdapter.notifyDataSetChanged()
        progressVisibilityGone()
    }

    private fun createRecycler() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initialization() {
        list = mutableListOf()
        newsAdapter = NewsAdapter(list, this)
    }

    private fun progressVisibilityGone() {
        progressCircular.visibility = View.GONE
    }

    override fun onItemClick(item: Article) {
        DetailsActivity.instanceActivity(activity, item)
    }

    private fun swipeListener(){
        swipeUp.setOnRefreshListener {
            list.clear()
            subscribeToTopHeadlines()
            showToast(context!!, "Данные успешно обновлены")
            swipeUp.isRefreshing = false
        }
    }

    private fun nestedScrollListener() {
        recycler_view.addOnScrollListener(object : PaginationScrollListener(linerManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }
            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }
            override fun loadMoreItems() {
                viewModel.isLoading = true
                subscribeToTopHeadlines()
            }
        })
    }
}