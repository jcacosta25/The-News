package io.jcal.thenews

import android.app.Activity
import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.jcal.thenews.di.ComponentsFactory
import io.jcal.thenews.di.component.AppComponent
import io.jcal.thenews.ui.utils.ReleaseTree
import timber.log.Timber
import javax.inject.Inject

class NewsApp : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = ComponentsFactory.createAppComponent(this)
        appComponent.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(object : timber.log.Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? =
                    super.createStackElementTag(element).plus(':').plus(element.lineNumber)
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}