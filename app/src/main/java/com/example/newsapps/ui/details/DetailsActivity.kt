package com.example.newsapps.ui.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsapps.Constants.ARTICLE
import com.example.newsapps.R
import com.example.newsapps.models.Article
import kotlinx.android.synthetic.main.activity_details.*

private lateinit var mViewModel: ViewModel

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initialization()
        getDataFromIntent()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        supportActionBar?.title = getString(R.string.details_tittle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @SuppressLint("SetTextI18n")
    private fun getDataFromIntent() {
        if (intent != null) {
            val article = intent?.getSerializableExtra(ARTICLE) as Article
            tvTittle.text = article.title
            tvDesc.text = article.description
            tvPublishedAt.text = getString(R.string.date_detail) + article.publishedAt
            tvUrl.text = getString(R.string.url_news) + article.url
            if (article.urlToImage != null){
                Glide.with(this).load(article.urlToImage).into(imageNews)
            } else {
                imageNews.setImageResource(R.drawable.not_foto)
            }
            if (article.author != null) {
                tvAuthor.text = article.author
            } else {
                tvAuthor.text = getString(R.string.author_unknown)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}