package `in`.nitin.newsapp.datasource.repository

import `in`.nitin.newsapp.BuildConfig
import `in`.nitin.newsapp.datasource.network.ApiFactory
import `in`.nitin.newsapp.datasource.network.ApiServices
import `in`.nitin.newsapp.datasource.network.ResponseHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteNewsDataSource @Inject constructor(private val apiFactory: ApiFactory) :
    ResponseHandler() {

    suspend fun fetchNewsList() =
        getResult {
            apiFactory.createService(ApiServices::class.java)
                .getNewsData(country = "in", apiKey = BuildConfig.API_KEY)
        }
}