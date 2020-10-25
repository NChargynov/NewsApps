package com.example.newsapps.ui.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapps.R
import com.example.newsapps.base.BaseActivity
import com.example.newsapps.extension.loadImage
import com.example.newsapps.models.Article
import com.example.newsapps.ui.news.NewsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.imageNews
import kotlinx.android.synthetic.main.activity_details.tvDesc
import kotlinx.android.synthetic.main.activity_details.tvTittle
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : BaseActivity<DetailsViewModel>(R.layout.activity_details) {

    override val viewModel by viewModel<DetailsViewModel>()

    override fun setUpViews() {
        setUpViewsDetails()
        initialization()
    }

    override fun setUpObservers() {
    }

    override fun setUpListeners() {
        setFavorite()
    }

    private fun setFavorite() {
        imageFavorite.setOnClickListener {
            if (item.isFavorite) {
                imageFavorite.setImageResource(R.drawable.ic_fav_like)
                viewModel.deleteFavoriteArticle(item)
            } else {
                imageFavorite.setImageResource(R.drawable.ic_favorites_like)
                viewModel.insertFavoriteNew(item)
            }

        }
    }

    private fun setUpViewsDetails() {
        tvTittle.text = item.title
        tvDesc.text = item.description
        tvUrl.text = item.url
        tvPublishedAt.text = item.publishedAt
        imageNews.loadImage(this, R.drawable.not_foto, item.urlToImage)
        tvAuthor.text = item.author
        if (item.isFavorite) {
            imageFavorite.setImageResource(R.drawable.ic_favorites_like)
        } else {
            imageFavorite.setImageResource(R.drawable.ic_fav_like)
        }
    }

    companion object {
        private lateinit var item: Article
        fun instanceActivity(activity: Activity?, item: Article) {
            this.item = item
            val intent = Intent(activity, DetailsActivity::class.java)
            activity?.startActivity(intent)
        }

    }

    private fun initialization() {
        supportActionBar?.title = getString(R.string.details_tittle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}