package org.byters.bctodo.view

import androidx.fragment.app.FragmentManager
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.view.ui.fragment.*
import org.byters.bctodo.view.utils.IHelperDialog
import java.lang.ref.WeakReference
import javax.inject.Inject

class Navigator(app: ApplicationToDo) : INavigator {

    //TODO implement cusom back stack

    @Inject
    lateinit var helperDialog: IHelperDialog

    init {
        app.component.inject(this)
    }

    var viewId: Int = 0
    lateinit var refFramgentManager: WeakReference<FragmentManager>

    override fun set(viewId: Int, fragmentManager: FragmentManager) {
        this.viewId = viewId
        this.refFramgentManager = WeakReference(fragmentManager)
    }

    override fun navigateListNotes() {
        if (refFramgentManager.get() == null) return

        refFramgentManager.get()
            ?.beginTransaction()
            ?.replace(viewId, FragmentListNotes())
            ?.commit()
    }

    override fun navigateNoteAdd() {
        if (refFramgentManager.get() == null) return

        refFramgentManager.get()
            ?.beginTransaction()
            ?.replace(viewId, FragmentNoteCreate())
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateNoteView() {
        if (refFramgentManager.get() == null) return

        refFramgentManager.get()
            ?.beginTransaction()
            ?.replace(viewId, FragmentNoteView())
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateNoteEdit() {
        if (refFramgentManager.get() == null) return

        refFramgentManager.get()
            ?.beginTransaction()
            ?.replace(viewId, FragmentNoteEdit())
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateTagList() {
        helperDialog.showDialogTagList()
    }

    override fun navigateFolders() {
        helperDialog.showDialogFolders()
    }

    override fun navigteErrorPermission() {
        if (refFramgentManager.get() == null) return

        //TODO clear backstack
        refFramgentManager.get()
            ?.beginTransaction()
            ?.replace(viewId, FragmentErrorPermission())
            ?.commit()
    }

    override fun navigateCurrent() {
    }

}
