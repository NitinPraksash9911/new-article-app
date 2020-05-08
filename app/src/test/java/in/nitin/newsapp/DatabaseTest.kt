package `in`.nitin.newsapp

import `in`.nitin.newsapp.application.NewsApplication
import `in`.nitin.newsapp.datasource.roomDb.NewsDao
import `in`.nitin.newsapp.datasource.roomDb.NewsDatabase
import `in`.nitin.newsapp.datasource.roomDb.entity.Article
import android.content.Context
import androidx.room.Room
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, sdk = [android.os.Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class DatabaseTest {
    lateinit var context: Context

    private var db: NewsDatabase? = null
    private var dao: NewsDao? = null


    @Before
    fun setup() {
        context = RuntimeEnvironment.application as NewsApplication
        db =
            Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).allowMainThreadQueries()
                .build()
        dao = db?.getNewsDao()
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertData() {
        val article1 = Article()
        article1.title = "Nitin"
        article1.description = "testDescription"
        article1.publishedAt = "20/May/2020"
        article1.author = "Prakash"
        article1.url = "www.google.com"
        dao?.insert(article1)
        val articleTest = dao!!.getArticle("Prakash")
        Assert.assertEquals(article1.author, articleTest.author)
    }

    @Test
    @Throws(Exception::class)
    fun shouldFlushData() {
        dao?.deleteAllArticle()
        Assert.assertEquals(dao?.getArticleCount(), 0)
    }

    @After
    fun endTest() {
        db!!.close()
    }


}