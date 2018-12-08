package org.byters.bctodo.view.ui.adapter

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterNoteCreateTagsAdapter
import org.byters.bctodo.view.presenter.callback.IPresenterNoteCreateTagsAdapterListener
import javax.inject.Inject

class AdapterTagsNoteCreate(applicationToDo: ApplicationToDo) : AdapterBase() {

    @Inject
    lateinit var presenterNoteCreatetagsAdapter: IPresenterNoteCreateTagsAdapter

    private var listenerPresenter: IPresenterNoteCreateTagsAdapterListener

    init {
        applicationToDo.component.inject(this)
        listenerPresenter = ListenerPresenter()
        presenterNoteCreatetagsAdapter.setListener(listenerPresenter)
    }

    override fun getItemViewType(position: Int): Int = presenterNoteCreatetagsAdapter.getItemType(position)

    override fun getItemCount(): Int = presenterNoteCreatetagsAdapter.getItemsNum()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase =
        if (viewType == presenterNoteCreatetagsAdapter.getTypeSettings()) ViewHolderSettings(parent)
        else ViewHolderItem(parent)

    inner class ViewHolderItem(parent: ViewGroup) : ViewHolderBase(R.layout.view_tag_list_item, parent),
        View.OnClickListener {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            itemView.setOnClickListener(this)
        }

        override fun setData(position: Int) {
            val title = presenterNoteCreatetagsAdapter.getItemTitle(position)
            tvTitle.setText(if (TextUtils.isEmpty(title)) "" else title)
            tvTitle.background = getBackground(position)
        }

        private fun getBackground(position: Int): Drawable {
            if (!presenterNoteCreatetagsAdapter.isSelected(position))
                return itemView.resources.getDrawable(R.drawable.bg_tag_deselected)

            val drawable = itemView.resources.getDrawable(R.drawable.bg_tag_selected)

            val color = presenterNoteCreatetagsAdapter.getItemColor(position) ?: return drawable

            drawable.setColorFilter(color, PorterDuff.Mode.SRC)

            return drawable
        }

        override fun onClick(v: View?) {
            presenterNoteCreatetagsAdapter.onClickItem(adapterPosition)
        }

    }

    inner class ViewHolderSettings(parent: ViewGroup) : ViewHolderBase(R.layout.view_tag_list_header, parent),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun setData(position: Int) {

        }

        override fun onClick(v: View?) {
            presenterNoteCreatetagsAdapter.onClickSettings()
        }

    }

    inner class ListenerPresenter : IPresenterNoteCreateTagsAdapterListener {
        override fun updateData() = notifyDataSetChanged()
    }

}
