package `in`.nitin.newsapp.di


import `in`.nitin.newsapp.datasource.roomDb.DATABASE_NAME
import `in`.nitin.newsapp.datasource.roomDb.NewsDao
import `in`.nitin.newsapp.datasource.roomDb.NewsDatabase
import `in`.nitin.newsapp.di.qualifier.DatabaseInfo
import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule(private val mContext: Application) {

    @DatabaseInfo
    private val mDBName =
        DATABASE_NAME

    @Singleton
    @Provides
    fun provideDatabase(): NewsDatabase {
        return Room.databaseBuilder(
            mContext,
            NewsDatabase::class.java,
            mDBName
        ).fallbackToDestructiveMigration().build()
    }



    @Singleton
    @Provides
    fun provideNoteDao(db: NewsDatabase): NewsDao {
        return db.getNewsDao()
    }


    /**
     * [fallbackToDestructiveMigration] :: when we increase the version number of database we have to tell room
     * how to migrate to the new schema  and if don't do this and increase version number our app will actually crash
     *  and we get illegalStateException.
     * By using [fallbackToDestructiveMigration] we can avoid the above exception because it will delete the database and
     *  all its' tables and create the fresh database from the scratch
     * */

}
