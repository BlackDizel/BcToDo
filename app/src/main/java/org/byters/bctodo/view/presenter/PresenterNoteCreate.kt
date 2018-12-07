package org.byters.bctodo.view.presenter

import android.graphics.Typeface
import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.controller.data.memorycache.ICacheFolders
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.memorycache.ICacheNoteCreate
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.controller.data.util.IHelperNotesSelected
import org.byters.bctodo.controller.data.util.callback.IHelperNotesSelectedListener
import org.byters.bctodo.model.FontEnum
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterNoteCreateListener
import org.byters.bctodo.view.utils.IHelperPopup
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterNoteCreate(app: ApplicationToDo) : IPresenterNoteCreate {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var helperPopup: IHelperPopup

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    @Inject
    lateinit var cacheNoteCreate: ICacheNoteCreate

    @Inject
    lateinit var navigator: INavigator

    @Inject
    lateinit var helperNotesSelected: IHelperNotesSelected

    @Inject
    lateinit var cacheFolders: ICacheFolders

    var refListener: WeakReference<IPresenterNoteCreateListener>? = null

    private var listenerFolders: IHelperNotesSelectedListener

    init {
        app.component.inject(this)
        listenerFolders = ListenerFolders()
        helperNotesSelected.addListener(listenerFolders)
    }

    override fun onClickSave(title: String, body: String) {
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(body)) {
            helperPopup.showToast(R.string.note_empty);
            return
        }
        cacheNotes.add(title, body, cacheNoteCreate.getSelectedIds(), helperNotesSelected.getFolderId())
        refListener?.get()?.finish()
    }

    override fun onClickFolder() {
        navigator.navigateFolders()
    }

    override fun setListener(listener: IPresenterNoteCreateListener) {
        refListener = WeakReference(listener)
    }

    override fun onCreateView() {
        setData()
    }

    private fun setData() {
        refListener?.get()
            ?.setData(
                if (cacheInterfaceState.getFont() == FontEnum.SANS) Typeface.SANS_SERIF else Typeface.SERIF,
                cacheFolders.getItemTitle(helperNotesSelected.getFolderId())
            )
    }

    inner class ListenerFolders : IHelperNotesSelectedListener {
        override fun onDataUpdated() {
            setData()
        }

    }
}
