package io.jcal.thenews

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.jcal.thenews.di.ComponentsFactory
import io.jcal.thenews.di.component.AppComponent
import javax.inject.Inject

class NewsApp : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = ComponentsFactory.createAppComponent(this)
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    companion object {
        @JvmStatic
        fun getAppComponent(context: Context): AppComponent = (context.applicationContext as NewsApp).appComponent
    }
}