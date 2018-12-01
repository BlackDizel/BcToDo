package org.byters.bctodo.view.presenter

import androidx.fragment.app.FragmentManager
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.memorycache.callback.ICacheInterfaceStateListener
import org.byters.bctodo.model.ThemeEnum
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.ui.activity.ActivityBase
import org.byters.bctodo.view.utils.IHelperDialog
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterActivityMain(app: ApplicationToDo) : IPresenterActivityMain {

    @Inject
    lateinit var navigator: INavigator

    @Inject
    lateinit var helperDialog: IHelperDialog

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    private var listenerCacheInterfaceState: ICacheInterfaceStateListener

    private var refListener: WeakReference<IPresenterActivityMainListener>? = null

    init {
        app.component.inject(this)
        listenerCacheInterfaceState = ListenerCacheInterfaceState()
        cacheInterfaceState.addListener(listenerCacheInterfaceState)
    }

    inner class ListenerCacheInterfaceState : ICacheInterfaceStateListener {

        override fun onThemeUpdate() {
            refListener?.get()?.restart()
        }
    }

    override fun onCreate() {
        refListener?.get()
            ?.setTheme(if (cacheInterfaceState.getTheme() == ThemeEnum.LIGHT) R.style.AppThemeLight else R.style.AppThemeDark)
    }

    override fun onActivityCreate(
        activity: ActivityBase,
        flContent: Int,
        supportFragmentManager: FragmentManager
    ) {
        navigator.set(flContent, supportFragmentManager)
        helperDialog.set(activity)
        navigate()
    }

    private fun navigate() {
        navigator.navigateListNotes()
    }

    override fun setListener(listenerPresenter: IPresenterActivityMainListener) {
        refListener = WeakReference(listenerPresenter)
    }

}
