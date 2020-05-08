package `in`.nitin.newsapp.datasource.repository

import `in`.nitin.newsapp.datasource.helper.resultLiveData
import `in`.nitin.newsapp.datasource.roomDb.NewsDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [NOTE]
 * [Repository] implementation saves api service responses into the database.
 * Changes to the database then trigger callbacks on active LiveData objects. Using this model,
 * the database serves as the [SingleSourceOfTruth], and
 * other parts of the app access it using our Repository.
 * Regardless of whether you use a disk cache,
 * we recommend that your repository designate a data source as the single source of truth for the rest of your app.
 *
 * */

@Singleton
class NewsDataRepository @Inject constructor(
    private var newsDao: NewsDao,
    private var remoteNewsDataSource: RemoteNewsDataSource
) {

    val articleList = resultLiveData(
        databaseQuery = { newsDao.getAllArticle() },
        networkCall = { remoteNewsDataSource.fetchNewsList() },
        saveCallResult = { newsDao.insertOrUpdateAllArticle(it.articles!!) }
    )

}