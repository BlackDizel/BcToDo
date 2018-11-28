package org.byters.bctodo.view.ui.adapter

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterTagsAdapter
import org.byters.bctodo.view.presenter.callback.IPresenterTagsAdapterListener
import javax.inject.Inject

class AdapterTags(app: ApplicationToDo) : AdapterBase() {

    @Inject
    lateinit var presenterTagsAdapter: IPresenterTagsAdapter

    private var listenerPresenter: IPresenterTagsAdapterListener

    init {
        app.component.inject(this)
        listenerPresenter = ListenerPresenter()
        presenterTagsAdapter.setListener(listenerPresenter)
    }

    override fun getItemViewType(position: Int): Int = presenterTagsAdapter.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase {
        if (viewType == presenterTagsAdapter.getTypeHeader())
            return ViewHolderTagHeader(parent)
        if (viewType == presenterTagsAdapter.getTypeOther())
            return ViewHolderTagOther(parent)
        else return ViewHolderTagItem(parent)
    }

    override fun getItemCount(): Int = presenterTagsAdapter.getItemsNum()

    inner class ViewHolderTagHeader(parent: ViewGroup) : ViewHolderBase(R.layout.view_tag_list_header, parent),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun setData(position: Int) {

        }

        override fun onClick(v: View?) {
            presenterTagsAdapter.onClickHeader()
        }

    }

    inner class ViewHolderTagItem(parent: ViewGroup) : ViewHolderBase(R.layout.view_tag_list_item, parent),
        View.OnClickListener {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            itemView.setOnClickListener(this)
        }

        override fun setData(position: Int) {
            val title = presenterTagsAdapter.getItemTitle(position)
            tvTitle.setText(if (TextUtils.isEmpty(title)) "" else title)
            tvTitle.setBackgroundResource(if (presenterTagsAdapter.isSelected(position)) R.drawable.bg_tag_selected else R.drawable.bg_tag_deselected)
        }

        override fun onClick(v: View?) {
            presenterTagsAdapter.onClickItem(adapterPosition)
        }
    }

    inner class ViewHolderTagOther(parent: ViewGroup) : ViewHolderBase(R.layout.view_tag_list_item, parent),
        View.OnClickListener {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            itemView.setOnClickListener(this)
        }

        override fun setData(position: Int) {
            tvTitle.setText(R.string.title_tag_other)
            tvTitle.setBackgroundResource(if (presenterTagsAdapter.isSelected(position)) R.drawable.bg_tag_selected else R.drawable.bg_tag_deselected)
        }

        override fun onClick(v: View?) {
            presenterTagsAdapter.onClickItem(adapterPosition)
        }
    }

    inner class ListenerPresenter : IPresenterTagsAdapterListener {
        override fun updateData() {
            notifyDataSetChanged()
        }
    }

}
