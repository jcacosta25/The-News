package io.jcal.thenews.di.component

import dagger.Component
import io.jcal.thenews.di.modules.NewsModule
import io.jcal.thenews.di.scopes.FragmentScope
import io.jcal.thenewsprovider.di.NetworkModule
import io.jcal.thenewsprovider.di.RepositoryModule
import io.jcal.thenewsprovider.di.StorageModule

@Component(
    dependencies = [AppComponent::class],
    modules = [NewsModule::class, RepositoryModule::class, StorageModule::class, NetworkModule::class]
)
@FragmentScope
interface FragmentComponent {
}