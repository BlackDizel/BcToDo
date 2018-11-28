package org.byters.bctodo.view.ui.adapter

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterTagsAdapter
import javax.inject.Inject

class AdapterTags(app: ApplicationToDo) : AdapterBase() {

    @Inject
    lateinit var presenterTagsAdapter: IPresenterTagsAdapter

    init {
        app.component.inject(this)
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

    inner class ViewHolderTagItem(parent: ViewGroup) : ViewHolderBase(R.layout.view_tag_list_item, parent) {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }

        override fun setData(position: Int) {
            val title = presenterTagsAdapter.getItemTitle(position)
            tvTitle.setText(if (TextUtils.isEmpty(title)) "" else title)
        }
    }

    inner class ViewHolderTagOther(parent: ViewGroup) : ViewHolderBase(R.layout.view_tag_list_item, parent) {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }

        override fun setData(position: Int) {
            tvTitle.setText(R.string.title_tag_other)
        }
    }

}
