package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.view.INavigator
import javax.inject.Inject

class PresenterListNotes(app: ApplicationToDo) : IPresenterListNotes {
    @Inject
    lateinit var navigator: INavigator

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    init {
        app.component.inject(this)
    }

    override fun onClickAdd() {
        navigator.navigateNoteAdd()
    }

    override fun onClickStyle() {
        cacheInterfaceState.setStyleNext()
    }

    override fun onClickTheme() {
        cacheInterfaceState.setThemeNext()
    }


}
