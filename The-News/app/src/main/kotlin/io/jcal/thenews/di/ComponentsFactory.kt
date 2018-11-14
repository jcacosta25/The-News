package io.jcal.thenews.di

import android.app.Application
import io.jcal.thenews.di.component.AppComponent
import io.jcal.thenews.di.component.DaggerAppComponent
import io.jcal.thenews.di.component.DaggerFragmentComponent
import io.jcal.thenews.di.component.FragmentComponent

class ComponentsFactory {
    companion object {
        @JvmStatic
        fun createAppComponent(app: Application): AppComponent =
            DaggerAppComponent.builder().application(app).build()

        @JvmStatic
        fun createFragmentComponent(appComponent: AppComponent): FragmentComponent =
            DaggerFragmentComponent.builder().appComponent(appComponent).build()
    }
}