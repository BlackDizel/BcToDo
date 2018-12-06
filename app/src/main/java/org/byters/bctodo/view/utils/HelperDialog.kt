package org.byters.bctodo.view.utils

import android.content.Context
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.view.ui.dialog.DialogBase
import org.byters.bctodo.view.ui.dialog.DialogFolderOptions
import org.byters.bctodo.view.ui.dialog.DialogFolders
import org.byters.bctodo.view.ui.dialog.DialogTagList
import org.byters.bctodo.view.ui.dialog.callback.IDialogFolderMoreListener
import java.lang.ref.WeakReference

class HelperDialog(app: ApplicationToDo) : IHelperDialog {

    private var refDialog: WeakReference<DialogBase>? = null

    private var refContext: WeakReference<Context>? = null

    private var refDialogFolderOptions: WeakReference<DialogFolderOptions>? = null

    override fun set(context: Context) {
        refContext = WeakReference(context)
    }

    private fun showDialog(dialog: DialogBase) {
        refDialog?.get()?.cancel()
        refDialog = WeakReference(dialog)
        refDialog?.get()?.show()

    }

    override fun showDialogTagList() {
        val context = refContext?.get() ?: return
        showDialog(DialogTagList(context))
    }


    override fun showDialogFolders() {
        val context = refContext?.get() ?: return
        showDialog(DialogFolders(context))
    }

    override fun showDialogFolderOptions(listener: IDialogFolderMoreListener) {
        val context = refContext?.get() ?: return

        refDialogFolderOptions?.get()?.cancel()
        refDialogFolderOptions = WeakReference(DialogFolderOptions(context, listener))
        refDialogFolderOptions?.get()?.show()
    }


}
