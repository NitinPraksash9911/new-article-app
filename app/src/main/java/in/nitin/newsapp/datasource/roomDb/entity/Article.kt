package `in`.nitin.newsapp.datasource.roomDb.entity

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article_table")
class Article {

    @SerializedName("publishedAt")
    @PrimaryKey
    @Expose
    @NonNull
    var publishedAt: String? = null

    /**
     * embedded nested object
     * */
    @SerializedName("source")
    @Expose
    @Embedded
    var source: Source? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val article = other as Article
        return publishedAt.equals(article.publishedAt)
    }

    override fun hashCode(): Int {
        var result = publishedAt!!.toInt()
        result = 31 * result + (source?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (urlToImage?.hashCode() ?: 0)
        result = 31 * result + (publishedAt?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        return result
    }
}