package org.byters.bctodo.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.byters.bctodo.R

class FragmentListNotes : FragmentBase() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_notes, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

    }

}