package `in`.nitin.newsapp.datasource.roomDb

import `in`.nitin.newsapp.datasource.roomDb.entity.Article
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAllArticle(articleList: List<Article>)


    @Query("SELECT * FROM article_table")
    fun getAllArticle(): LiveData<List<Article>>

    /*All below methods used for testing*/
    @Insert
    fun insert(article: Article)

    @Query("SELECT * FROM article_table WHERE author = :author")
    fun getArticle(author: String): Article


    @Query("DELETE  FROM article_table")
    fun deleteAllArticle()

    @Query("SELECT count(*) FROM article_table")
    fun getArticleCount(): Int

}