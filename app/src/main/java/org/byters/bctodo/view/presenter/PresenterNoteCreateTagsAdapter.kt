package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheNoteCreate
import org.byters.bctodo.controller.data.memorycache.ICacheTags
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterNoteCreateTagsAdapterListener
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterNoteCreateTagsAdapter(app: ApplicationToDo) :
    IPresenterNoteCreateTagsAdapter {

    @Inject
    lateinit var cacheTags: ICacheTags

    @Inject
    lateinit var cacheNoteCreate: ICacheNoteCreate

    @Inject
    lateinit var navigator:INavigator

    init {
        app.component.inject(this)
    }

    private var refListenerPresenter: WeakReference<IPresenterNoteCreateTagsAdapterListener>? = null

    override fun setListener(listenerPresenter: IPresenterNoteCreateTagsAdapterListener) {
        refListenerPresenter = WeakReference(listenerPresenter)
    }

    override fun getItemType(position: Int): Int = if (position == 0) getTypeSettings() else getTypeItem()

    private fun getTypeItem(): Int = TypeEnum.ITEM.value

    override fun getTypeSettings(): Int = TypeEnum.SETTINGS.value

    override fun getItemsNum(): Int = cacheTags.getItemsNum() + 1

    override fun getItemTitle(position: Int): String = cacheTags.getItemTitle(position - 1)

    override fun isSelected(position: Int): Boolean = cacheNoteCreate.isSelected(cacheTags.getId(position - 1))

    override fun onClickItem(adapterPosition: Int) {
        cacheNoteCreate.setSelected(cacheTags.getId(adapterPosition - 1), !isSelected(adapterPosition))
        refListenerPresenter?.get()?.updateData()
    }

    override fun onClickSettings() {
        navigator.navigateTagList()
    }

    enum class TypeEnum(val value: Int) {
        SETTINGS(1),
        ITEM(2)
    }

}
