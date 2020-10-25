package com.example.newsapps.ui.news.fragments.favorite

import com.example.newsapps.R
import com.example.newsapps.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FavoritesViewModel>(R.layout.favorites_fragment) {

    override val viewModel by viewModel<FavoritesViewModel>()


    override fun setUpViews() {
    }

    override fun setUpObservers() {
    }

    override fun setUpListeners() {
    }

}