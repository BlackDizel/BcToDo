package org.byters.bctodo.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterListNotes
import org.byters.bctodo.view.presenter.callback.IPresenterListNotesListener
import org.byters.bctodo.view.ui.adapter.AdapterListNotes
import org.byters.bctodo.view.ui.adapter.AdapterTags
import org.byters.bctodo.view.ui.view.ViewSearch
import org.byters.bctodo.view.ui.view.callback.IViewSearchListener
import javax.inject.Inject

class FragmentListNotes : FragmentBase(), View.OnClickListener {

    @Inject
    lateinit var presenterListNotes: IPresenterListNotes

    private var rvTags: RecyclerView? = null
    private var vNotesFilter: View? = null

    private lateinit var listenerPresenter: IPresenterListNotesListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as ApplicationToDo).component.inject(this)
        listenerPresenter = ListenerPresenter()
        presenterListNotes.setListener(listenerPresenter)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_notes, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val rvItems: RecyclerView = view.findViewById(R.id.rvItems)
        rvItems.layoutManager = LinearLayoutManager(context)
        rvItems.setHasFixedSize(true)
        rvItems.adapter = AdapterListNotes(rvItems, context!!.applicationContext as ApplicationToDo)

        view.findViewById<View>(R.id.ivAdd).setOnClickListener(this)
        view.findViewById<View>(R.id.ivListNotesStyle).setOnClickListener(this)
        view.findViewById<View>(R.id.ivTheme).setOnClickListener(this)
        view.findViewById<View>(R.id.ivFont).setOnClickListener(this)
        view.findViewById<View>(R.id.ivTags).setOnClickListener(this)

        view.findViewById<ViewSearch>(R.id.vSearch).setListener(ListenerSearch())

        vNotesFilter = view.findViewById(R.id.llNotesFilter)

        rvTags = view.findViewById(R.id.rvTags)
        rvTags!!.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = AdapterTags(context!!.applicationContext as ApplicationToDo)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.ivAdd)
            presenterListNotes.onClickAdd()
        if (v.id == R.id.ivListNotesStyle)
            presenterListNotes.onClickStyle()
        if (v.id == R.id.ivTheme)
            presenterListNotes.onClickTheme()
        if (v.id == R.id.ivFont)
            presenterListNotes.onClickFont()
        if (v.id == R.id.ivTags)
            presenterListNotes.onClickTags()
    }

    inner class ListenerPresenter : IPresenterListNotesListener {

        override fun setVisibilityTags(isVisible: Boolean) {
            if (!isAdded) return
            rvTags!!.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    inner class ListenerSearch : IViewSearchListener {
        override fun onHide() {
            vNotesFilter!!.visibility = View.VISIBLE
        }

        override fun onShow() {
            vNotesFilter!!.visibility = View.GONE
        }

        override fun onQuery(query: String?) {
            //todo request search
        }

    }

}