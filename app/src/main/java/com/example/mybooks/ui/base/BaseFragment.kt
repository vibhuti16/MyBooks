package com.example.mybooks.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.mybooks.app.di.component.ApplicationComponent
import com.example.mybooks.ui.App

abstract class  BaseFragment: Fragment(){

    abstract var layoutId: Int

    abstract fun setupInjection(applicationComponent: ApplicationComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection(App.graph)
    }

    protected fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(layoutId, container, false)
        ButterKnife.bind(this, view)
        return view
    }


}