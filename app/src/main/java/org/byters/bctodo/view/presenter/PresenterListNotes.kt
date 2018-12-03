package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.util.IHelperNotesSelected
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterListNotesListener
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterListNotes(app: ApplicationToDo) : IPresenterListNotes {


    @Inject
    lateinit var navigator: INavigator

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    @Inject
    lateinit var helperNotesList:IHelperNotesSelected

    private var refListener: WeakReference<IPresenterListNotesListener>? = null

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

    override fun onClickFont() {
        cacheInterfaceState.setFontNext()
    }

    override fun setListener(listenerPresenter: IPresenterListNotesListener) {
        refListener = WeakReference(listenerPresenter)
    }

    override fun onClickTags() {
        cacheInterfaceState.setTagsVisibility(!cacheInterfaceState.isTagsVisible())
        refListener?.get()?.setVisibilityTags(cacheInterfaceState.isTagsVisible())
    }

    override fun onQueryEmpty() {
        helperNotesList.setQuery(null)
    }

    override fun onQuery(query: String?) {
        helperNotesList.setQuery(query)
    }
}
