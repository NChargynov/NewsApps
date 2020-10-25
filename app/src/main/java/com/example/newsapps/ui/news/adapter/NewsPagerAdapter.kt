package com.example.newsapps.ui.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.newsapps.ui.news.fragments.everything.EverythingFragment
import com.example.newsapps.ui.news.fragments.favorite.FavoritesFragment
import com.example.newsapps.ui.news.fragments.favorite.FavoritesViewModel
import com.example.newsapps.ui.news.fragments.top_headlines.TopHeadlinesFragment

class NewsPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> EverythingFragment()
            1 -> TopHeadlinesFragment()
            2 -> FavoritesFragment()
            else -> EverythingFragment()
        }
    }
}