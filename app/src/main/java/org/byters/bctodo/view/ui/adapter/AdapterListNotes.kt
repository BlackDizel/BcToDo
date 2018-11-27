package org.byters.bctodo.view.ui.adapter

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.model.StyleEnum
import org.byters.bctodo.view.presenter.IPresenterListNotesAdapter
import org.byters.bctodo.view.presenter.callback.IPresenterListNotesAdapterListener
import javax.inject.Inject

class AdapterListNotes(val rvItems: RecyclerView, app: ApplicationToDo) : AdapterBase() {

    @Inject
    lateinit var presenterListNotesAdapter: IPresenterListNotesAdapter

    private val listenerPresenter: AdapterListNotes.ListenerPresenter

    init {
        app.component.inject(this)
        listenerPresenter = ListenerPresenter()
        presenterListNotesAdapter.setListener(listenerPresenter)
    }

    inner class ListenerPresenter : IPresenterListNotesAdapterListener {

        override fun onUpdateStyle() {
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int = presenterListNotesAdapter.getStyle().type

    override fun getItemCount(): Int = presenterListNotesAdapter.getItemsNum()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase =
        if (viewType == StyleEnum.SMALL.type)
            ViewHolderSingleLine(parent)
        else if (viewType == StyleEnum.MEDIUM.type)
            ViewHolderMedium(parent)
        else ViewHolderFull(parent)

    inner abstract class ViewHolderNoteBase(resId: Int, parent: ViewGroup) :
        ViewHolderBase(resId, parent), View.OnClickListener {

        val tvDate: TextView

        init {
            tvDate = itemView.findViewById(R.id.tvDate)
            itemView.setOnClickListener(this)
        }

        override fun setData(position: Int) {
            val text = presenterListNotesAdapter.getItemDate(position)
            tvDate.text = if (!TextUtils.isEmpty(text)) text else ""
        }

        override fun onClick(v: View?) {
            presenterListNotesAdapter.onClick(adapterPosition)
        }

    }

    inner class ViewHolderSingleLine(parent: ViewGroup) :
        ViewHolderNoteBase(R.layout.view_note_list_item_single_line, parent), View.OnClickListener {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }

        override fun setData(position: Int) {
            super.setData(position)
            val text = presenterListNotesAdapter.getItemTitleSingleLine(position)
            tvTitle.text = if (!TextUtils.isEmpty(text)) text else ""

        }

    }

    inner class ViewHolderMedium(parent: ViewGroup) :
        ViewHolderNoteBase(R.layout.view_note_list_item_medium, parent), View.OnClickListener {

        val tvTitle: TextView
        val tvBody: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvBody = itemView.findViewById(R.id.tvBody)
        }

        override fun setData(position: Int) {
            super.setData(position)
            val text = presenterListNotesAdapter.getItemTitle(position)
            tvTitle.text = if (!TextUtils.isEmpty(text)) text else ""

            val body = presenterListNotesAdapter.getItemBody(position)
            tvBody.text = if (!TextUtils.isEmpty(body)) body else ""

        }

    }

    inner class ViewHolderFull(parent: ViewGroup) :
        ViewHolderNoteBase(R.layout.view_note_list_item_full, parent), View.OnClickListener {

        val tvTitle: TextView
        val tvBody: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvBody = itemView.findViewById(R.id.tvBody)
        }

        override fun setData(position: Int) {
            super.setData(position)
            val text = presenterListNotesAdapter.getItemTitle(position)
            tvTitle.text = if (!TextUtils.isEmpty(text)) text else ""

            val body = presenterListNotesAdapter.getItemBody(position)
            tvBody.text = if (!TextUtils.isEmpty(body)) body else ""

        }

    }

}
