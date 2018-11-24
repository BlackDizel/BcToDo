package org.byters.bctodo.view.presenter

import androidx.fragment.app.FragmentManager
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.view.INavigator
import javax.inject.Inject

class PresenterActivityMain(app: ApplicationToDo) : IPresenterActivityMain {

    @Inject
    lateinit var navigator: INavigator

    init {
        app.component.inject(this)
    }

    override fun onActivityCreate(flContent: Int, supportFragmentManager: FragmentManager) {
        navigator.set(flContent, supportFragmentManager)
    }

}
