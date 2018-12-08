package org.byters.bctodo.view.utils

import android.content.Context
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.view.ui.dialog.*
import org.byters.bctodo.view.ui.dialog.callback.IDialogColorSelectListener
import org.byters.bctodo.view.ui.dialog.callback.IDialogFolderMoreListener
import org.byters.bctodo.view.ui.dialog.callback.IDialogTagOptionsListener
import java.lang.ref.WeakReference

class HelperDialog(app: ApplicationToDo) : IHelperDialog {

    private var refDialogDrawer: WeakReference<DialogBase>? = null
    private var refDialog: WeakReference<DialogBase>? = null

    private var refContext: WeakReference<Context>? = null

    override fun set(context: Context) {
        refContext = WeakReference(context)
    }

    private fun showDialogDrawer(dialog: DialogBase) {
        refDialogDrawer?.get()?.cancel()
        refDialogDrawer = WeakReference(dialog)
        refDialogDrawer?.get()?.show()
    }

    private fun showDialog(dialog: DialogBase) {
        refDialog?.get()?.cancel()
        refDialog = WeakReference(dialog)
        refDialog?.get()?.show()

    }

    override fun showDialogTagList() {
        val context = refContext?.get() ?: return
        showDialogDrawer(DialogTagList(context))
    }


    override fun showDialogFolders() {
        val context = refContext?.get() ?: return
        showDialogDrawer(DialogFolders(context))
    }

    override fun showDialogFolderOptions(
        listener: IDialogFolderMoreListener,
        showDelete: Boolean
    ) {
        val context = refContext?.get() ?: return
        showDialog(DialogFolderOptions(context, listener, showDelete))
    }

    override fun showDialogTagOptions(listener: IDialogTagOptionsListener) {
        val context = refContext?.get() ?: return
        showDialog(DialogTagOptions(context, listener))
    }

    override fun showDialogColorSelect(listener: IDialogColorSelectListener) {
        val context = refContext?.get() ?: return
        showDialog(DialogColorSelect(context, listener))
    }

}
