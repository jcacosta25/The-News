package io.jcal.thenews.di

import android.app.Application
import io.jcal.thenews.di.component.AppComponent
import io.jcal.thenews.di.component.DaggerAppComponent

class ComponentsFactory {
    companion object {
        @JvmStatic
        fun createAppComponent(app: Application): AppComponent =
            DaggerAppComponent.builder().application(app).build()
    }
}