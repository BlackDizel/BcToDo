package org.byters.bctodo.view.presenter

import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.controller.data.memorycache.ICacheTags
import org.byters.bctodo.view.ui.dialog.callback.IDialogColorSelectListener
import org.byters.bctodo.view.ui.dialog.callback.IDialogTagOptionsListener
import org.byters.bctodo.view.utils.IHelperDialog
import org.byters.bctodo.view.utils.IHelperPopup
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterDialogTagListAdapter(app: ApplicationToDo) : IPresenterDialogTagListAdapter {

    @Inject
    lateinit var cacheTags: ICacheTags

    @Inject
    lateinit var cacheNotes: ICacheNotes

    @Inject
    lateinit var helperPopup: IHelperPopup

    @Inject
    lateinit var helperDialog: IHelperDialog

    private var colorLabel: Int? = null

    private var refListener: WeakReference<IPresenterDialogTagListAdapterListener>? = null

    private var listenerDialogOptions: IDialogTagOptionsListener

    private var listenerDialogColorLabel: IDialogColorSelectListener

    init {
        app.component.inject(this)
        listenerDialogOptions = ListenerDialogOptions()
        listenerDialogColorLabel = ListenerDialogLabelColor()
    }

    override fun setListener(listenerPresenter: IPresenterDialogTagListAdapterListener) {
        refListener = WeakReference(listenerPresenter)
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) getTypeTagAdd() else TypeEnum.ITEM.value

    override fun getItemsNum(): Int = cacheTags.getItemsNum() + 1

    override fun getTypeTagAdd(): Int = TypeEnum.ITEM_ADD.value

    override fun onClickAdd(title: String) {
        if (TextUtils.isEmpty(title)) {
            helperPopup.showToast(R.string.error_tag_title_empty)
        }
        cacheTags.addTag(title, colorLabel)
        notifyListener()
    }

    override fun onClickMore(adapterPosition: Int) {
        cacheTags.setSelectedPopup(adapterPosition - 1)
        helperDialog.showDialogTagOptions(listenerDialogOptions)
    }


    private fun notifyListener() {
        refListener?.get()?.updateData()
    }

    override fun getItemTitle(position: Int): String? = cacheTags.getItemTitle(position - 1)

    override fun getColorLabel(position: Int): Int? = cacheTags.getItemColor(position - 1)

    enum class TypeEnum(val value: Int) {
        ITEM_ADD(1),
        ITEM(2)
    }

    inner class ListenerDialogOptions : IDialogTagOptionsListener {

        override fun onRemove() {
            val tagId = cacheTags.getSelectedPopupId()
            if (TextUtils.isEmpty(tagId)) return
            cacheTags.removeTag(tagId!!)
            cacheNotes.removeTags(tagId!!)
            notifyListener()
        }

        override fun onEdit(title: String) {
            cacheTags.updateTitle(cacheTags.getSelectedPopupId(), title)
            notifyListener()
        }
    }

    override fun onClickLabelColor() {
        helperDialog.showDialogColorSelect(listenerDialogColorLabel)
    }

    override fun getColorLabelTagCreate(): Int? = colorLabel

    inner class ListenerDialogLabelColor : IDialogColorSelectListener {
        override fun onColorSelect(color: Int) {
            colorLabel = color
            refListener?.get()?.updateData()
        }

    }


}

