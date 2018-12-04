package org.byters.bctodo.view.presenter

import android.text.TextUtils
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.controller.data.memorycache.ICacheNotes
import org.byters.bctodo.controller.data.memorycache.ICacheTags
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

    private var refListener: WeakReference<IPresenterDialogTagListAdapterListener>? = null

    init {
        app.component.inject(this)
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
        cacheTags.addTag(title)
        notifyListener()
    }

    override fun onClickDelete(adapterPosition: Int) {
        val id: String? = cacheTags.getId(adapterPosition - 1)
        if (TextUtils.isEmpty(id)) return
        cacheTags.removeTag(id!!)
        cacheNotes.removeTags(id!!)
        notifyListener()
    }

    private fun notifyListener() {
        refListener?.get()?.updateData()
    }

    override fun getItemTitle(position: Int): String? = cacheTags.getItemTitle(position - 1)

    enum class TypeEnum(val value: Int) {
        ITEM_ADD(1),
        ITEM(2)
    }
}

