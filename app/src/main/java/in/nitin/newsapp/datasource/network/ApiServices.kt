package `in`.nitin.newsapp.datasource.network

import `in`.nitin.newsapp.datasource.roomDb.entity.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/v2/top-headlines")

    suspend fun getNewsData(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsData>

}