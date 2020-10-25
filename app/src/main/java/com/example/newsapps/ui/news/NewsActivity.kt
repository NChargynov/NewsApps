package com.example.newsapps.ui.news

import com.example.newsapps.R
import com.example.newsapps.base.BaseActivity
import com.example.newsapps.ui.news.adapter.NewsPagerAdapter
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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
                R.id.favorites -> changeCurrentPosition(2)
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
        viewPager.offscreenPageLimit = 3
    }

    override fun setUpObservers() {
    }

    override fun setUpListeners() {
    }
}