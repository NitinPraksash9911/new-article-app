package `in`.nitin.newsapp.viewmodel

import `in`.nitin.newsapp.datasource.repository.NewsDataRepository
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NewsDataViewModel @Inject constructor(private val newsDataRepository: NewsDataRepository) :
    ViewModel() {

    /**
     * [SingleDataSource]
     * getting news data from local database because we made it single data source for whole app
     *
     */
    var noteListdata = newsDataRepository.articleList

}