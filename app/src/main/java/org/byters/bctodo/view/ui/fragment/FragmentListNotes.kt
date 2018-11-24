package org.byters.bctodo.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.ui.adapter.AdapterListNotes

class FragmentListNotes : FragmentBase(),View.OnClickListener  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_notes, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val rvItems: RecyclerView = view.findViewById(R.id.rvItems)
        rvItems.layoutManager = LinearLayoutManager(context)
        rvItems.setHasFixedSize(true)
        rvItems.adapter = AdapterListNotes(context!!.applicationContext as ApplicationToDo)

        view.findViewById<View>(R.id.ivAdd).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //TODO open note create
    }

}