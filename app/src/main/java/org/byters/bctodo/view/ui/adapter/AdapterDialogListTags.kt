package org.byters.bctodo.view.ui.adapter

import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterDialogTagListAdapter
import org.byters.bctodo.view.presenter.IPresenterDialogTagListAdapterListener
import javax.inject.Inject

class AdapterDialogListTags(applicationToDo: ApplicationToDo) : AdapterBase() {


    @Inject
    lateinit var presenterDialogTagListAdapter: IPresenterDialogTagListAdapter

    private var listenerPresenter: IPresenterDialogTagListAdapterListener

    init {
        applicationToDo.component.inject(this)
        listenerPresenter = ListenerPresenter()
        presenterDialogTagListAdapter.setListener(listenerPresenter)

    }

    override fun getItemViewType(position: Int): Int = presenterDialogTagListAdapter.getItemViewType(position)

    override fun getItemCount(): Int = presenterDialogTagListAdapter.getItemsNum()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase =
        if (viewType == presenterDialogTagListAdapter.getTypeTagAdd())
            ViewHolderTagAdd(parent)
        else ViewHolderTag(parent)

    inner class ViewHolderTagAdd(parent: ViewGroup) : ViewHolderBase(R.layout.view_dialog_list_tag_add, parent),
        View.OnClickListener, TextView.OnEditorActionListener {

        private val etTitle: EditText

        init {
            itemView.setOnClickListener(this)
            etTitle = itemView.findViewById(R.id.etTitle)
            etTitle.setOnEditorActionListener(this)
        }

        override fun setData(position: Int) {
            etTitle.setText("")
        }

        override fun onClick(v: View?) {
            presenterDialogTagListAdapter.onClickAdd(etTitle.text.toString())
        }

        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            presenterDialogTagListAdapter.onClickAdd(etTitle.text.toString())
            return true
        }

    }

    inner class ViewHolderTag(parent: ViewGroup) : ViewHolderBase(R.layout.view_dialog_list_tag_item, parent),
        View.OnClickListener {

        private val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            itemView.findViewById<View>(R.id.ivTagMore).setOnClickListener(this)
        }

        override fun setData(position: Int) {
            val title = presenterDialogTagListAdapter.getItemTitle(position)
            tvTitle.text = if (TextUtils.isEmpty(title)) "" else title
        }

        override fun onClick(v: View?) {
            presenterDialogTagListAdapter.onClickMore(adapterPosition)
        }

    }

    inner class ListenerPresenter : IPresenterDialogTagListAdapterListener {

        override fun updateData() {
            notifyDataSetChanged()
        }
    }

}
