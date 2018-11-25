package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.controller.data.memorycache.callback.ICacheInterfaceStateListener
import org.byters.bctodo.model.StyleEnum
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterListNotesAdapterListener
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterListNotesAdapter(app: ApplicationToDo) : IPresenterListNotesAdapter {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var navigator: INavigator

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    private var refListener: WeakReference<IPresenterListNotesAdapterListener>? = null

    private val listenerCacgeInterfaceState: ICacheInterfaceStateListener

    init {
        app.component.inject(this)
        listenerCacgeInterfaceState = ListenerCacheInterfaceState()
        cacheInterfaceState.setListener(listenerCacgeInterfaceState)
    }


    override fun setListener(listenerPresenter: IPresenterListNotesAdapterListener) {
        refListener = WeakReference(listenerPresenter)
    }

    override fun getItemsNum(): Int = cacheNotes.getItemsNum()

    override fun getItemTitleSingleLine(position: Int): String? = cacheNotes.getItemTitleSingleLine(position)

    override fun onClick(adapterPosition: Int) {
        cacheNotes.setSelectedNote(adapterPosition)
        navigator.navigateNoteView()
    }

    override fun getStyle(): StyleEnum = cacheInterfaceState.getStyle()

    override fun getItemTitle(position: Int): String? = cacheNotes.getItemTitle(position)

    override fun getItemBody(position: Int): String? = cacheNotes.getItemBody(position)

    inner class ListenerCacheInterfaceState : ICacheInterfaceStateListener {
        override fun onStyleUpdate() {
            refListener?.get()?.onUpdateStyle()
        }

    }

}
