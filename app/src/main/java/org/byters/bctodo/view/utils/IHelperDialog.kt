package org.byters.bctodo.view.utils

import android.content.Context
import org.byters.bctodo.view.ui.dialog.callback.IDialogFolderMoreListener

interface IHelperDialog {
    fun showDialogTagList()

    fun set(context: Context)
    fun showDialogFolders()
    fun showDialogFolderOptions(listener:IDialogFolderMoreListener)
}
