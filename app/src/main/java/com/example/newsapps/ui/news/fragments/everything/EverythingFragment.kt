package com.example.newsapps.ui.news.fragments.everything
import android.view.*
import androidx.core.widget.doAfterTextChanged
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
import kotlinx.android.synthetic.main.fragment_everything.*
import kotlinx.android.synthetic.main.fragment_everything.progressCircular
import kotlinx.android.synthetic.main.fragment_everything.recycler_view
import kotlinx.android.synthetic.main.fragment_everything.swipeUp
import kotlinx.android.synthetic.main.fragment_topheadlines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EverythingFragment : BaseFragment<NewsViewModel>(R.layout.fragment_everything), NewsAdapter.OnItemClick {

    override val viewModel by viewModel<NewsViewModel>()
    private lateinit var list: MutableList<Article>
    private lateinit var newsAdapter: NewsAdapter
    private val linerManager = LinearLayoutManager(activity)
    private var bitcoin = "bitcoin"

    override fun setUpViews() {
        initialization()
        createRecycler()
    }

    override fun setUpObservers() {
        subscribeToEverything(bitcoin)
    }

    override fun setUpListeners() {
        editTextListener()
        nestedScrollListener()
        swipeListener()
    }

    private fun nestedScrollListener() {
        recycler_view.addOnScrollListener(object : PaginationScrollListener(linerManager){
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.isLoading = true
                subscribeToEverything(bitcoin)
            }
        })
    }

    private fun createRecycler() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun editTextListener() {
        editQuery.doAfterTextChanged {
            if (it != null) {
                if (editQuery.text.isNotEmpty()) {
                    list.clear()
                    subscribeToEverything(editQuery.text.toString())
                    newsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun swipeListener(){
        swipeUp.setOnRefreshListener {
            list.clear()
            subscribeToEverything(bitcoin)
            showToast(context!!, "Данные успешно обновлены")
            swipeUp.isRefreshing = false
        }
    }

    private fun initialization() {
        list = mutableListOf()
        newsAdapter = NewsAdapter(list, this)
    }

    private fun subscribeToEverything(query: String) {
        viewModel.deleteAllArticles()
        viewModel.getEverything(query).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    setTable(it.data!!.articles)
                    showToast(context!!, getString(R.string.news_success))
                }
            }
        })
    }

    private fun setTable(it: MutableList<Article>) {
        list.addAll(it)
        newsAdapter.notifyDataSetChanged()
        progressVisibilityGone()
    }

    private fun progressVisibilityGone() {
        progressCircular.visibility = View.GONE

    }

    override fun onItemClick(item: Article) {
        DetailsActivity.instanceActivity(activity, item)
    }
}
//        nestedScroll.setOnScrollChangeListener(
//            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
//                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
//                    if (viewModel.articles.value!!.size >= 10) {
//                        progressBarDown.visibility = View.VISIBLE
//                        if (isRequest!!) {
//                            viewModel.getEverything(bitcoin)
//                        } else {
//                            viewModel.getNewsTopHeadlines()
//                        }
//                        subscribeToNews()
//                    } else {
//                        showToast(context!!,getString(R.string.news_finish))
//                    }
//                }
//            })