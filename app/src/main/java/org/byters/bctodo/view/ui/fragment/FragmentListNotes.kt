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
import org.byters.bctodo.view.ui.adapter.AdapterListNotes
import org.byters.bctodo.view.ui.adapter.AdapterTags
import javax.inject.Inject

class FragmentListNotes : FragmentBase(), View.OnClickListener {

    @Inject
    lateinit var presenterListNotes: IPresenterListNotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as ApplicationToDo).component.inject(this)
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

        view.findViewById<RecyclerView>(R.id.rvTags).apply {
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
    }

}