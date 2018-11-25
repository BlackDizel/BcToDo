package org.byters.bctodo.view

import androidx.fragment.app.FragmentManager

interface INavigator {

    fun set(viewId: Int, fragmentManager: FragmentManager)
    fun navigateListNotes()
    fun navigateNoteAdd()
    fun navigateNoteView()
    fun navigateNoteEdit()
}
