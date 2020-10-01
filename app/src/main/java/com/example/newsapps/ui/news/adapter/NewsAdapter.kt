package com.example.newsapps.ui.news.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapps.R
import com.example.newsapps.models.Article
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_news.*

class NewsAdapter(private var list: MutableList<Article>,
                  private val onNewsClickListener: OnItemNewsClickListener):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onNewsClickListener.onNewsClickListener(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class NewsViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(article: Article){
            tvTittle.text = article.title
            tvDesc.text = article.description
            if (article.urlToImage != null){
                Glide.with(imageNews.context).load(article.urlToImage).into(imageNews)
            } else{
                imageNews.setImageResource(R.drawable.not_foto)
            }
        }
    }

    interface OnItemNewsClickListener{
        fun onNewsClickListener(article: Article)
    }
}
