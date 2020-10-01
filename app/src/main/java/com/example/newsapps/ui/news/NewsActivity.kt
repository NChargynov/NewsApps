package com.example.newsapps.ui.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapps.Constants.ARTICLE
import com.example.newsapps.R
import com.example.newsapps.extension.showToast
import com.example.newsapps.models.Article
import com.example.newsapps.ui.details.DetailsActivity
import com.example.newsapps.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity(), NewsAdapter.OnItemNewsClickListener {


    private lateinit var list: MutableList<Article>
    private lateinit var mViewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter
    private var flag: Boolean? = true
    private var isRequest: Boolean? = true
    private var page = 1
    private var amountPageForEverything = 8
    private var bitcoin = "bitcoin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()
        mViewModel.getEverything(bitcoin, amountPageForEverything)
        subscribeToNews()
        createRecycler()
        editTextListener()
        nestedScrollListener()
    }

    private fun nestedScrollListener() {
        nestedScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    if (mViewModel.articles.value!!.size >= 10) {
                        page++
                        progressBarDown.visibility = View.VISIBLE
                        if (isRequest!!) {
                            mViewModel.getEverything(bitcoin, page)
                        } else {
                            mViewModel.getNewsTopHeadlines(page)
                        }
                        subscribeToNews()
                    } else {
                        showToast(this, getString(R.string.news_finish))
                    }
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tool_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_change_news) {
            flag = if (flag!!) {
                showToast(this, getString(R.string.news_head))
                list.clear()
                page = 1
                mViewModel.getNewsTopHeadlines(page)
                subscribeToNews()
                item.setIcon(R.drawable.ic_top_headlines)
                isRequest = false
                false
            } else {
                showToast(this, getString(R.string.news_ever))
                list.clear()
                mViewModel.getEverything(bitcoin, amountPageForEverything)
                item.setIcon(R.drawable.ic_change)
                subscribeToNews()
                isRequest = true
                page = 1
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createRecycler() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            adapter = this@NewsActivity.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun editTextListener() {
        editQuery.doAfterTextChanged {
            if (it != null) {
                if (editQuery.text.isNotEmpty()) {
                    list.clear()
                    mViewModel.getEverything(editQuery.text.toString(), amountPageForEverything)
                    subscribeToNews()
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        list = mutableListOf()
        adapter = NewsAdapter(list, this)
    }

    private fun subscribeToNews() {
        mViewModel.articles.observe(this, Observer {
            list.addAll(it)
            adapter.notifyDataSetChanged()
            showToast(this, getString(R.string.news_success))
            progressVisibilityGone()
        })
    }

    private fun progressVisibilityGone() {
        progressBarDown.visibility = View.GONE
        progressCircular.visibility = View.GONE

    }

    override fun onNewsClickListener(article: Article) {
        startActivity(
            Intent(this, DetailsActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .putExtra(ARTICLE, article)
        )
    }
}