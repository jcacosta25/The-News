package io.jcal.thenews.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.jcal.thenews.di.ComponentsFactory
import io.jcal.thenews.di.component.AppComponent

abstract class BaseFragment : Fragment() {

    protected lateinit var appComponent: AppComponent

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appComponent = ComponentsFactory.createAppComponent(requireActivity().application)
    }
}