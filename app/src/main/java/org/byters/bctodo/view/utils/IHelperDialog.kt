package org.byters.bctodo.view.utils

import android.content.Context
import org.byters.bctodo.view.ui.dialog.callback.IDialogColorSelectListener
import org.byters.bctodo.view.ui.dialog.callback.IDialogFolderMoreListener
import org.byters.bctodo.view.ui.dialog.callback.IDialogTagOptionsListener

interface IHelperDialog {
    fun showDialogTagList()

    fun set(context: Context)
    fun showDialogFolders()
    fun showDialogFolderOptions(
        listener: IDialogFolderMoreListener,
        showDelete: Boolean
    )
    fun showDialogTagOptions(listener: IDialogTagOptionsListener)
    fun showDialogColorSelect(listener: IDialogColorSelectListener)
}
