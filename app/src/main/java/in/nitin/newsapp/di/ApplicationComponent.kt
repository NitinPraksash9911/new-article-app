package `in`.nitin.newsapp.di

import `in`.nitin.newsapp.application.NewsApplication
import `in`.nitin.newsapp.di.qualifier.ApplicationContext
import `in`.nitin.newsapp.ui.NewsListFragment
import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, ApplicationModule::class, DatabaseModule::class]
)
interface ApplicationComponent {

    fun inject(newsListFragment: NewsListFragment?)

    fun inject(myapplication: NewsApplication?)


    @ApplicationContext
    fun getApplication(): Application;

}