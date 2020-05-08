package `in`.nitin.newsapp.datasource.roomDb.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsData {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("code")
    @Expose
     val code: String? = null

    @SerializedName("message")
    @Expose
     val message: String? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null

}