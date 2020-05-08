package `in`.nitin.newsapp.di

import `in`.mvvm.apparchsinglesource.di.ViewModelKey
import `in`.nitin.newsapp.viewmodel.ViewModelProviderFactory
import `in`.nitin.newsapp.viewmodel.NewsDataViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsDataViewModel::class)
    abstract fun bindWebViewModel(myViewModel: NewsDataViewModel): ViewModel
}