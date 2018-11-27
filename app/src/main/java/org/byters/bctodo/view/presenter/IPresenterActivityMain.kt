package org.byters.bctodo.view.presenter

import androidx.fragment.app.FragmentManager

interface IPresenterActivityMain {
    fun onActivityCreate(flContent: Int, supportFragmentManager: FragmentManager)
    fun setListener(listenerPresenter: IPresenterActivityMainListener)
    fun onCreate()
}
