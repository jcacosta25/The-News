package io.jcal.thenews.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.jcal.thenews.di.scopes.ViewModelKey
import io.jcal.thenews.ui.viewmodel.NewsViewModel
import io.jcal.thenews.ui.viewmodel.ViewModelFactoryEx

@Module(includes = [NewsAppModule::class])
abstract class NewsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryEx): ViewModelProvider.Factory

}