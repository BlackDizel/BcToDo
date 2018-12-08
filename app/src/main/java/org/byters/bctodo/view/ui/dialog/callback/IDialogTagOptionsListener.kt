package org.byters.bctodo.view.ui.dialog.callback

interface IDialogTagOptionsListener {

    fun onRemove()
    fun onEdit(title: String, colorSelected: Int?)
}
