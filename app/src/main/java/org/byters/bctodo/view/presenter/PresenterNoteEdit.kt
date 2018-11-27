package org.byters.bctodo.view.presenter

import android.graphics.Typeface
import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.model.FontEnum
import org.byters.bctodo.view.presenter.callback.IPresenterNoteEditListener
import org.byters.bctodo.view.utils.IHelperPopup
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterNoteEdit(app: ApplicationToDo) : IPresenterNoteEdit {

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var helperPopup: IHelperPopup

    lateinit var refListener: WeakReference<IPresenterNoteEditListener>

    init {
        app.component.inject(this)
    }

    override fun onClickSave(title: String, body: String) {
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(body)) {
            helperPopup.showToast(R.string.note_empty);
            return
        }
        cacheNotes.editSelected(title, body)
        refListener.get()?.finish()
    }


    override fun setListener(listener: IPresenterNoteEditListener) {
        refListener = WeakReference(listener)
    }

    override fun onCreateView() {
        refListener.get()?.setData(
            cacheNotes.getTitleSelected(),
            cacheNotes.getBodySelected(),
            if (cacheInterfaceState.getFont() == FontEnum.SANS) Typeface.SANS_SERIF else Typeface.SERIF
        )
    }

}
