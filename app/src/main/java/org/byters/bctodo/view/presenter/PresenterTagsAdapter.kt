package org.byters.bctodo.view.presenter

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.ICacheTags
import org.byters.bctodo.view.INavigator
import javax.inject.Inject

class PresenterTagsAdapter(app: ApplicationToDo) :
    IPresenterTagsAdapter {

    @Inject
    lateinit var cacheTags: ICacheTags

    @Inject
    lateinit var navigator: INavigator

    init {
        app.component.inject(this)
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

    enum class TypeEnum(val type: Int) {
        HEADER(1), ITEM(2), OTHER(3)
    }

}
