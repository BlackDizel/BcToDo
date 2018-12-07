package org.byters.bctodo.view.ui.adapter

import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterDialogFoldersAdapter
import org.byters.bctodo.view.presenter.callback.IPresenterDialogFoldersAdapterListener
import org.byters.bctodo.view.ui.adapter.callback.IDialogFolderAdapterListener
import java.lang.ref.WeakReference
import java.util.*
import javax.inject.Inject

class AdapterDialogFolders(app: ApplicationToDo, listenerDialogAdapter:IDialogFolderAdapterListener?) : AdapterBase() {

    @Inject
    lateinit var presenterDialogFoldersAdapter: IPresenterDialogFoldersAdapter

    private val listenerPresenter: ListenerPresenter

    private var folderId: String? = null

    private var refListenerDialog: WeakReference<IDialogFolderAdapterListener?>

    init {
        app.component.inject(this)
        listenerPresenter = ListenerPresenter()
        refListenerDialog = WeakReference(listenerDialogAdapter)
        presenterDialogFoldersAdapter.addListener(listenerPresenter)
    }

    fun setData(parentFolderId: String?, position: Int) {
        folderId = presenterDialogFoldersAdapter.getFolderId(parentFolderId, position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = presenterDialogFoldersAdapter.getItemsNum(folderId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase =
        ViewHolderFolder(parent)

    inner class ViewHolderFolder(parent: ViewGroup) : ViewHolderBase(R.layout.view_dialog_folers_item, parent),
        View.OnClickListener, TextView.OnEditorActionListener {

        private var tvFolder: TextView
        private var tvFolderAdd: TextView
        private var llFolderAdd: View

        private var rvItems: RecyclerView

        private var adapter: AdapterDialogFolders

        private var ivFolderShow: ImageView

        init {
            tvFolder = itemView.findViewById(R.id.tvFolder)
            rvItems = itemView.findViewById(R.id.rvItems)
            tvFolderAdd = itemView.findViewById(R.id.etFolderAdd)
            llFolderAdd = itemView.findViewById(R.id.llFolderAdd)
            ivFolderShow = itemView.findViewById(R.id.ivFolderShow)

            ivFolderShow.setOnClickListener(this)
            itemView.findViewById<View>(R.id.ivFolderAddComplete).setOnClickListener(this)
            itemView.findViewById<View>(R.id.ivFolderAddCancel).setOnClickListener(this)
            itemView.findViewById<View>(R.id.ivFolderMore).setOnClickListener(this)
            itemView.setOnClickListener(this)

            tvFolderAdd.setOnEditorActionListener(this)

            adapter = AdapterDialogFolders(itemView.context.applicationContext as ApplicationToDo, refListenerDialog?.get())
            rvItems.layoutManager = LinearLayoutManager(itemView.context)
            rvItems.adapter = adapter
            //todo set shared view pool
        }

        override fun setData(position: Int) {
            tvFolder.text = presenterDialogFoldersAdapter.getFolderTitle(folderId, position)
            adapter.setData(folderId, position)

            llFolderAdd.visibility = if (presenterDialogFoldersAdapter.isVisibleFolderAdd(
                    folderId, position
                )
            ) View.VISIBLE else View.GONE

            val foldersVisible = presenterDialogFoldersAdapter.isVisibleFolders(folderId, position)

            rvItems.visibility = if (foldersVisible) View.VISIBLE else View.GONE

            ivFolderShow.visibility =
                    if (presenterDialogFoldersAdapter.isVisibleFolderShow(
                            folderId,
                            position
                        )
                    ) View.VISIBLE else View.GONE

            ivFolderShow.setImageResource(if (foldersVisible) R.drawable.ic_keyboard_arrow_up_white_24dp else R.drawable.ic_keyboard_arrow_down_white_24dp)

        }

        override fun onClick(v: View?) {
            if (v == null) return

            if (v == itemView) {
                presenterDialogFoldersAdapter.onClickItem(folderId, adapterPosition)
                refListenerDialog?.get()?.onClickItem()
            }

            if (v.id == R.id.ivFolderShow)
                presenterDialogFoldersAdapter.onClickFolderShow(folderId, adapterPosition)

            if (v.id == R.id.ivFolderAddCancel)
                presenterDialogFoldersAdapter.onClickFolderAddCancel()

            if (v.id == R.id.ivFolderAddComplete)
                addFolder()

            if (v.id == R.id.ivFolderMore)
                presenterDialogFoldersAdapter.onClickMore(folderId, adapterPosition)
        }

        private fun addFolder() {
            presenterDialogFoldersAdapter.onClickFolderAddComplete(
                tvFolderAdd.text.toString(),
                folderId,
                adapterPosition
            )
            tvFolderAdd.text = ""
        }

        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            addFolder()
            return true
        }
    }

    inner class ListenerPresenter : IPresenterDialogFoldersAdapterListener {

        private val name: String = UUID.randomUUID().toString()
        override fun getName(): String? = name

        override fun updateData() {
            notifyDataSetChanged()
        }
    }

}
