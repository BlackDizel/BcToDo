package org.byters.bctodo.view.presenter

import android.graphics.Typeface
import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.memorycache.ICacheNoteCreate
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
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

    var refListener: WeakReference<IPresenterNoteCreateListener>? = null

    init {
        app.component.inject(this)
    }

    override fun onClickSave(title: String, body: String) {
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(body)) {
            helperPopup.showToast(R.string.note_empty);
            return
        }
        cacheNotes.add(title, body, cacheNoteCreate.getSelectedIds())
        refListener?.get()?.finish()
    }

    override fun onClickFolder() {
        navigator.navigateFolders()
    }

    override fun setListener(listener: IPresenterNoteCreateListener) {
        refListener = WeakReference(listener)
    }

    override fun onCreateView() {
        refListener?.get()
            ?.setData(if (cacheInterfaceState.getFont() == FontEnum.SANS) Typeface.SANS_SERIF else Typeface.SERIF)
    }

}
