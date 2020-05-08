package `in`.nitin.newsapp.ui.adapter

import `in`.nitin.newsapp.R
import `in`.nitin.newsapp.databinding.ListItemViewBinding
import `in`.nitin.newsapp.datasource.roomDb.entity.Article
import `in`.nitin.newsapp.ui.utils.SpannableClass
import `in`.nitin.newsapp.ui.utils.SpannableClass.color
import `in`.nitin.newsapp.ui.utils.SpannableClass.plus
import `in`.nitin.newsapp.ui.utils.SpannableClass.underline
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(private var clickListener: ClickListener) :
    ListAdapter<Article, NewsListAdapter.NewsViewHolder>(ArticleDataDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            , clickListener

        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class NewsViewHolder(
        private val binding: ListItemViewBinding,
        private var clickListener: ClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindTo(article: Article) {
            binding.newsData = article

            val spanned = SpannableClass.spannable {
                var description = article.description
                if (description.isNullOrEmpty()) {
                    description = itemView.context.getString(R.string.na)

                } else {
                    description = article.description

                }
                color(Color.WHITE, description!!) +
                        underline(
                            color(
                                Color.YELLOW,
                                itemView.context.getString(R.string.read_more)
                            )
                        )
            }

            binding.descTextView.text = spanned

            binding.descTextView.setOnClickListener {
                clickListener.onItemClick(article)
            }


            binding.executePendingBindings()


        }
    }

    class ArticleDataDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.publishedAt.equals(newItem.publishedAt)
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.equals(newItem)
        }
    }

    interface ClickListener {

        fun onItemClick(article: Article)
    }
}