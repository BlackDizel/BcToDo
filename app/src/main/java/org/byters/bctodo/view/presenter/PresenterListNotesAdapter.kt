package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.memorycache.callback.ICacheInterfaceStateListener
import org.byters.bctodo.controller.data.util.IHelperNotesSelected
import org.byters.bctodo.controller.data.util.callback.IHelperNotesSelectedListener
import org.byters.bctodo.model.FontEnum
import org.byters.bctodo.model.StyleEnum
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterListNotesAdapterListener
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import javax.inject.Inject

class PresenterListNotesAdapter(app: ApplicationToDo) : IPresenterListNotesAdapter {

    @Inject
    lateinit var helperNotesSelected: IHelperNotesSelected

    @Inject
    lateinit var navigator: INavigator

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    private var refListener: WeakReference<IPresenterListNotesAdapterListener>? = null

    private val listenerCacheInterfaceState: ICacheInterfaceStateListener

    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy MMM dd HH:mm")

    private var listenerHelperNotes: IHelperNotesSelectedListener

    init {
        app.component.inject(this)
        listenerCacheInterfaceState = ListenerCacheInterfaceState()
        cacheInterfaceState.addListener(listenerCacheInterfaceState)
        listenerHelperNotes = ListenerHelperNotes()
        helperNotesSelected.addListener(listenerHelperNotes)
    }

    inner class ListenerHelperNotes : IHelperNotesSelectedListener {
        override fun onDataUpdated() {
            refListener?.get()?.onUpdateStyle()
        }
    }


    override fun setListener(listenerPresenter: IPresenterListNotesAdapterListener) {
        refListener = WeakReference(listenerPresenter)
    }

    override fun getItemsNum(): Int = helperNotesSelected.getItemsNum()

    override fun getItemTitleSingleLine(position: Int): String? = helperNotesSelected.getItemTitleSingleLine(position)

    override fun onClick(adapterPosition: Int) {
        helperNotesSelected.setSelectedNote(adapterPosition)
        navigator.navigateNoteView()
    }

    override fun getStyle(): StyleEnum = cacheInterfaceState.getStyle()

    override fun getItemTitle(position: Int): String? = helperNotesSelected.getItemTitle(position)

    override fun getItemBody(position: Int): String? = helperNotesSelected.getItemBody(position)

    override fun getItemDate(position: Int): String? {
        val date = helperNotesSelected.getItemDate(position)
        if (date == null) return null
        return dateFormat.format(date)
    }

    inner class ListenerCacheInterfaceState : ICacheInterfaceStateListener {
        override fun onStyleUpdate() {
            refListener?.get()?.onUpdateStyle()
        }

        override fun onFontUpdate() {
            refListener?.get()?.onUpdateStyle()
        }

    }

    override fun getItemFont(): FontEnum = cacheInterfaceState.getFont()

}
