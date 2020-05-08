package `in`.nitin.newsapp.di

import `in`.nitin.newsapp.di.qualifier.ApplicationContext
import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val mApplication: Application) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplication(): Application {
        return mApplication
    }

}