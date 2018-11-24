package org.byters.bctodo.view

import androidx.fragment.app.FragmentManager
import org.byters.bctodo.ApplicationToDo
import java.lang.ref.WeakReference

class Navigator(app: ApplicationToDo) : INavigator {

    //TODO implement cusom back stack

    init {

    }

    var viewId: Int = 0
    lateinit var refFramgentManager: WeakReference<FragmentManager>

    override fun set(viewId: Int, fragmentManager: FragmentManager) {
        this.viewId = viewId
        this.refFramgentManager = WeakReference(fragmentManager)
    }

}
