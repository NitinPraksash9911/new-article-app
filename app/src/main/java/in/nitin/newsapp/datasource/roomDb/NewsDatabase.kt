package `in`.nitin.newsapp.datasource.roomDb

import `in`.nitin.newsapp.datasource.roomDb.entity.Article
import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_NAME = "news_database"

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao  // to access newDao
}