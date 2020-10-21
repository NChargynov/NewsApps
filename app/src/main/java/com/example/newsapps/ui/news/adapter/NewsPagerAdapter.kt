package com.example.newsapps.ui.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.newsapps.ui.news.everything.EverythingFragment
import com.example.newsapps.ui.news.top_headlines.TopHeadlinesFragment

class NewsPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> EverythingFragment()
            1 -> TopHeadlinesFragment()
            else -> EverythingFragment()
        }
    }

}