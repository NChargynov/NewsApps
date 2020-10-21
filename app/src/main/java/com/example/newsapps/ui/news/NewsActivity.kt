package com.example.newsapps.ui.news

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapps.R
import com.example.newsapps.base.BaseActivity
import com.example.newsapps.extension.showToast
import com.example.newsapps.models.Article
import com.example.newsapps.network.Status
import com.example.newsapps.ui.details.DetailsActivity
import com.example.newsapps.ui.news.adapter.NewsAdapter
import com.example.newsapps.ui.news.adapter.NewsPagerAdapter
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.FieldPosition

class NewsActivity : BaseActivity<NewsViewModel>(R.layout.activity_news){

    override val viewModel by viewModel<NewsViewModel>()
    private lateinit var viewPagerAdapter: NewsPagerAdapter

    override fun setUpViews() {
        setupViewPager()
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.everything -> changeCurrentPosition(0)
                R.id.topHeadlines -> changeCurrentPosition(1)
            }
            true
        }
    }

    private fun changeCurrentPosition(position: Int) {
        viewPager.currentItem = position
    }

    private fun setupViewPager() {
        viewPagerAdapter = NewsPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = 2
    }

    override fun setUpObservers() {
    }

    override fun setUpListeners() {
    }
}