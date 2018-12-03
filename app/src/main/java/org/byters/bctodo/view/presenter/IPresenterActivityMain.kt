package org.byters.bctodo.view.presenter

import androidx.fragment.app.FragmentManager
import org.byters.bctodo.view.ui.activity.ActivityBase

interface IPresenterActivityMain {
    fun onActivityCreate(activity: ActivityBase, flContent: Int, supportFragmentManager: FragmentManager)
    fun setListener(listenerPresenter: IPresenterActivityMainListener)
    fun onCreate()
    fun onResume(activityMain: ActivityBase)
    fun onPermissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
}
