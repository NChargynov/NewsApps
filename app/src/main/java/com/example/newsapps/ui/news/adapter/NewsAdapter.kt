package com.example.newsapps.ui.news.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapps.R
import com.example.newsapps.extension.loadImage
import com.example.newsapps.models.Article
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*

class NewsAdapter(private var list: MutableList<Article>,
                  private val onClick: OnItemClick):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class NewsViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(item: Article){
            tvTittle.text = item.title
            tvDesc.text = item.description
            imageNews.loadImage(imageNews.context, R.drawable.not_foto, item.urlToImage)
        }
    }

    interface OnItemClick{
        fun onItemClick(item: Article)
    }
}
