package org.byters.bctodo.view.presenter

import android.graphics.Typeface
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheInterfaceState
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.model.FontEnum
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterNoteViewListener
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterNoteView(app: ApplicationToDo) : IPresenterNoteView {

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var cacheInterfaceState: ICacheInterfaceState

    @Inject
    lateinit var navigator: INavigator

    private lateinit var refListener: WeakReference<IPresenterNoteViewListener>

    init {
        app.component.inject(this)
    }

    override fun setListener(listener: IPresenterNoteViewListener) {
        refListener = WeakReference(listener)
    }


    override fun onCreateView() {
        refListener.get()?.setData(
            cacheNotes.getTitleSelected(),
            cacheNotes.getBodySelected(),
            if (cacheInterfaceState.getFont() == FontEnum.SANS) Typeface.SANS_SERIF else Typeface.SERIF
        )
    }

    override fun onClickEdit() {
        refListener.get()?.finish()
        navigator.navigateNoteEdit()
    }

    override fun onClickDelete() {
        cacheNotes.removeSelected()
        refListener.get()?.finish()
    }
}
