package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheTags
import org.byters.bctodo.controller.data.memorycache.callback.ICacheTagListener
import org.byters.bctodo.view.INavigator
import org.byters.bctodo.view.presenter.callback.IPresenterTagsAdapterListener
import java.lang.ref.WeakReference
import javax.inject.Inject

class PresenterTagsAdapter(app: ApplicationToDo) :
    IPresenterTagsAdapter {

    @Inject
    lateinit var cacheTags: ICacheTags

    @Inject
    lateinit var navigator: INavigator

    private val listenerCacheTags: ICacheTagListener

    init {
        app.component.inject(this)
        listenerCacheTags = ListenerCacheTags()
        cacheTags.addListener(listenerCacheTags)
    }

    private var refListener: WeakReference<IPresenterTagsAdapterListener>? = null

    override fun setListener(listenerPresenter: IPresenterTagsAdapterListener) {
        refListener = WeakReference(listenerPresenter)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) getTypeHeader()
        else if (isLastItem(position)) getTypeOther()
        else TypeEnum.ITEM.type
    }

    override fun getTypeHeader(): Int = TypeEnum.HEADER.type
    override fun getTypeOther(): Int = TypeEnum.OTHER.type

    override fun getItemsNum(): Int = cacheTags.getItemsNum() + 2

    override fun onClickHeader() = navigator.navigateTagList()

    override fun getItemTitle(position: Int): String = cacheTags.getItemTitle(position - 1)

    override fun isSelected(position: Int): Boolean =
        if (isLastItem(position)) cacheTags.isSelectedWithoutTag() else cacheTags.isSelected(position - 1)

    private fun isLastItem(position: Int): Boolean = position == getItemsNum() - 1

    override fun onClickItem(adapterPosition: Int) {
        if (isLastItem(adapterPosition)) cacheTags.setSelectedWithoutTag(!cacheTags.isSelectedWithoutTag())
        else cacheTags.setSelected(adapterPosition - 1, !cacheTags.isSelected(adapterPosition - 1))
    }

    enum class TypeEnum(val type: Int) {
        HEADER(1), ITEM(2), OTHER(3)
    }

    inner class ListenerCacheTags : ICacheTagListener {
        override fun onDataUpdate() {
            refListener?.get()?.updateData()
        }
    }

}
