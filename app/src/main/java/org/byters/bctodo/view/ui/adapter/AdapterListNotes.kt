package org.byters.bctodo.view.ui.adapter

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterListNotesAdapter
import javax.inject.Inject

class AdapterListNotes(app: ApplicationToDo) : AdapterBase() {

    @Inject
    lateinit var presenterListNotesAdapter: IPresenterListNotesAdapter

    init {
        app.component.inject(this)
    }

    override fun getItemCount(): Int = presenterListNotesAdapter.getItemsNum()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase = ViewHolderSingleLine(parent)

    inner class ViewHolderSingleLine(parent: ViewGroup) :
        ViewHolderBase(R.layout.view_note_list_item_single_line, parent), View.OnClickListener {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            itemView.setOnClickListener(this)
        }

        override fun setData(position: Int) {
            val text = presenterListNotesAdapter.getItemTitleSingleLine(position)
            tvTitle.text = if (!TextUtils.isEmpty(text)) text else ""

        }

        override fun onClick(v: View?) {
            presenterListNotesAdapter.onClick(adapterPosition)
        }

    }

}
