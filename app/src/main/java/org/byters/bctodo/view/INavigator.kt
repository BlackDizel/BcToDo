package org.byters.bctodo.view

import androidx.fragment.app.FragmentManager
import org.byters.bctodo.view.ui.dialog.callback.IDialogFolderMoreListener

interface INavigator {

    fun set(viewId: Int, fragmentManager: FragmentManager)
    fun navigateListNotes()
    fun navigateNoteAdd()
    fun navigateNoteView()
    fun navigateNoteEdit()
    fun navigateTagList()
    fun navigateFolders()
    fun navigteErrorPermission()
    fun navigateCurrent()
    fun navigateFolderOptions(
        listener: IDialogFolderMoreListener,
        showDelete: Boolean
    )
}
