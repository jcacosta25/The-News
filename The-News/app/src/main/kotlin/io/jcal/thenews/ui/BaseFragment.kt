package io.jcal.thenews.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.jcal.thenews.NewsApp
import io.jcal.thenews.di.ComponentsFactory
import io.jcal.thenews.di.component.FragmentComponent

open class BaseFragment:Fragment() {

    private lateinit var  fragmentComponent: FragmentComponent

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent = ComponentsFactory.createFragmentComponent(NewsApp.getAppComponent(context!!))
    }
}