package com.example.newsapps.ui.details
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapps.R
import com.example.newsapps.extension.loadImage
import com.example.newsapps.models.Article
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.imageNews
import kotlinx.android.synthetic.main.activity_details.tvDesc
import kotlinx.android.synthetic.main.activity_details.tvTittle

private lateinit var mViewModel: ViewModel

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initialization()
        setUpViews()
    }

    private fun setUpViews() {
        tvTittle.text = item?.title
        tvDesc.text = item?.description
        tvUrl.text = item?.url
        tvPublishedAt.text = item?.publishedAt
        imageNews.loadImage(this, R.drawable.not_foto, item?.urlToImage)
        if (item?.author != null) {
            tvAuthor.text = item?.author
        } else {
            tvAuthor.text = getString(R.string.author_unknown)
        }
//        Glide.with(this).asBitmap()
//            .placeholder(R.drawable.not_foto)
//            .load(item?.urlToImage).into(imageNews)
    }

    companion object {
        private var item: Article? = null
        fun instanceActivity(activity: Activity?, item: Article) {
            this.item = item
            val intent = Intent(activity, DetailsActivity::class.java)
            activity?.startActivity(intent)
        }

    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        supportActionBar?.title = getString(R.string.details_tittle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}