package org.byters.bctodo.view.utils

import android.content.Context
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.view.ui.dialog.DialogTagList
import java.lang.ref.WeakReference

class HelperDialog(app: ApplicationToDo) : IHelperDialog {

    private var refDialogTagList: WeakReference<DialogTagList>? = null

    private var refContext: WeakReference<Context>? = null

    override fun set(context: Context) {
        refContext = WeakReference(context)
    }

    override fun showDialogTagList() {
        val context = refContext?.get() ?: return

        refDialogTagList?.get()?.cancel()
        val dialogTagList = DialogTagList(context)
        dialogTagList.show()
        refDialogTagList = WeakReference(dialogTagList)
    }


}
